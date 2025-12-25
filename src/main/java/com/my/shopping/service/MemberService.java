package com.my.shopping.service;

import com.my.shopping.domain.member.Member;
import com.my.shopping.domain.member.dto.*;

import java.util.Map;

public interface MemberService {
    void insert(MemberCreateDto memberCreateDto);
    Member findById(Long id);
    int update(MemberUpdateDto memberUpdateDto);
    int delete(Long id);

    Member login(MemberLoginDto memberLoginDto);

    Boolean isDuplicatedLoginId(String loginId);
    Member findByKakaoId(String kakaoId);
    Member joinByKakao(KakaoUserInfo kakaoUser);
    Map<String, String> validate(MemberValidDto validDto);
    void validateMemberAccess(Long loginMemberId, Long targetMemberId);
}