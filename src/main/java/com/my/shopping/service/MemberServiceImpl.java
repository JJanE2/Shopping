package com.my.shopping.service;

import com.my.shopping.domain.member.Member;
import com.my.shopping.domain.member.dto.MemberCreateDto;
import com.my.shopping.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;

    @Override
    @Transactional
    public void insert(MemberCreateDto memberCreateDto) {
        memberMapper.insert(memberCreateDto);
    }

    @Override
    public Member findById(Long id) {




        return memberMapper.findById(id);
    }

    @Override
    @Transactional
    public int update(String newNickname, String newPassword, Long id) {
        return memberMapper.update(newNickname, newPassword, id);
    }

    @Override
    @Transactional
    public int delete(Long id) {
        return memberMapper.delete(id);
    }
}