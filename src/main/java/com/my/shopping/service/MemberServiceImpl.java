package com.my.shopping.service;

import com.my.shopping.domain.cart.dto.CartCreateDto;
import com.my.shopping.domain.member.Member;
import com.my.shopping.domain.member.dto.MemberCreateDto;
import com.my.shopping.domain.member.dto.MemberLoginDto;
import com.my.shopping.domain.member.dto.MemberUpdateDto;
import com.my.shopping.mapper.CartMapper;
import com.my.shopping.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;
    private final CartMapper cartMapper;

    @Override
    @Transactional
    public void insert(MemberCreateDto memberCreateDto) {
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

        if (findMember != null && findMember.getPassword().equals(memberLoginDto.getPassword())) {
            return findMember;
        }
        return null;
    }

    @Override
    public Boolean isDuplicatedLoginId(String loginId) {
        Member findMember = memberMapper.findByLoginId(loginId);
        return findMember != null;
    }
}