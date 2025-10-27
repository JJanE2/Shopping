package com.my.shopping.mapper;

import com.my.shopping.domain.member.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    void insert(Member member);
    Member findById(Long id);
    int update(@Param(value = "newNickname") String newNickname,
               @Param(value = "newPassword") String newPassword,
               @Param(value = "id") Long id);
    int delete(Long id);

}
