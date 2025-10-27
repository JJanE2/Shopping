package com.my.shopping.service;

import com.my.shopping.domain.member.Member;
import org.apache.ibatis.annotations.Param;

public interface MemberService {
    void insert(Member member);
    Member findById(Long id);
    int update(String newNickname, String newPassword, Long id);
    int delete(Long id);
}