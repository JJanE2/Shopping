package com.my.shopping.service;

import com.my.shopping.domain.cart.dto.CartCreateDto;
import com.my.shopping.domain.member.Member;
import com.my.shopping.domain.member.dto.*;
import com.my.shopping.mapper.CartMapper;
import com.my.shopping.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;
    private final CartMapper cartMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void insert(MemberCreateDto memberCreateDto) {
        String encodedPwd = passwordEncoder.encode(memberCreateDto.getPassword());
        memberCreateDto.setPassword(encodedPwd);
        memberMapper.insert(memberCreateDto);
        cartMapper.insertCart(new CartCreateDto(null, memberCreateDto.getId()));
    }

    @Override
    public Member findById(Long id) {




        return memberMapper.findById(id);
    }

    @Override
    @Transactional
    public int update(MemberUpdateDto memberUpdateDto) {
        String encodedPwd = passwordEncoder.encode(memberUpdateDto.getPassword());
        memberUpdateDto.setPassword(encodedPwd);
        return memberMapper.update(memberUpdateDto);
    }

    @Override
    @Transactional
    public int delete(Long id) {
        return memberMapper.delete(id);
    }

    @Override
    public Member login(MemberLoginDto memberLoginDto) {
        Member findMember = memberMapper.findByLoginId(memberLoginDto.getLoginId());

        if (findMember != null && passwordEncoder.matches(memberLoginDto.getPassword(), findMember.getPassword())) {
            return findMember;
        }
        return null;
    }

    @Override
    public Boolean isDuplicatedLoginId(String loginId) {
        Member findMember = memberMapper.findByLoginId(loginId);
        return findMember != null;
    }

    @Override
    public Member findByKakaoId(String kakaoId) {
        return memberMapper.findByKakaoId(kakaoId);
    }

    @Override
    @Transactional
    public Member joinByKakao(KakaoUserInfo kakaoUser) {
        MemberCreateDto createDto = new MemberCreateDto();
        createDto.setKakaoId(kakaoUser.getId());
        createDto.setEmail(kakaoUser.getEmail());
        createDto.setNickname(kakaoUser.getNickname());
        createDto.setRole("CUSTOMER");

        memberMapper.insert(createDto);
        Member member = memberMapper.findByKakaoId(kakaoUser.getId());
        return member;
    }

    @Override
    public Map<String, String> validate(MemberValidDto validDto) {
        String loginIdReg = "^[a-zA-Z0-9]{1,10}$";
        String nicknameReg = "^[a-zA-Z0-9가-힣]{1,8}$";
        String passwordReg = "^(?=.*([A-Za-z]|\\d|[@$!%*#?&])).{1,16}$";


        Map<String, String> errors = new HashMap<>();
        // loginId validation
        if (validDto.getLoginId() == null || !validDto.getLoginId().matches(loginIdReg)) {
            errors.put(
                    "loginId",
                    "아이디는 영문, 숫자 1~10자만 가능합니다"
            );
        }
        // nickName validation
        if (validDto.getNickname() == null || !validDto.getNickname().matches(nicknameReg)) {
            errors.put(
                    "nickname",
                    "닉네임은 한글, 영문, 숫자 1~8자만 가능합니다"
            );
        }
        // password validation
        if (validDto.getPassword() == null || !validDto.getPassword().matches(passwordReg)) {
            errors.put(
                    "password",
                    "비밀번호는 영문, 숫자, 특수문자 1~16자만 가능합니다"
            );
        }
        return errors;
    }
}