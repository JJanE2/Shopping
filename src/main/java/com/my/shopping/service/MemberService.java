package com.my.shopping.service;

import com.my.shopping.domain.member.Member;
import com.my.shopping.domain.member.dto.KakaoUserInfo;
import com.my.shopping.domain.member.dto.MemberCreateDto;
import com.my.shopping.domain.member.dto.MemberLoginDto;
import com.my.shopping.domain.member.dto.MemberUpdateDto;
import org.apache.ibatis.annotations.Param;

public interface MemberService {
    void insert(MemberCreateDto memberCreateDto);
    Member findById(Long id);
    int update(MemberUpdateDto memberUpdateDto);
    int delete(Long id);

    Member login(MemberLoginDto memberLoginDto);

    Boolean isDuplicatedLoginId(String loginId);
    Member findByKakaoId(String kakaoId);
    Member joinByKakao(KakaoUserInfo kakaoUser);
}