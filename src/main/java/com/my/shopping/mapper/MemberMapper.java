package com.my.shopping.mapper;

import com.my.shopping.domain.member.Member;
import com.my.shopping.domain.member.dto.MemberCreateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    void insert(MemberCreateDto memberCreateDto);
    Member findById(Long id);
    Member findByLoginId(@Param(value = "loginId") String loginId);
    int update(@Param(value = "newNickname") String newNickname,
               @Param(value = "newPassword") String newPassword,
               @Param(value = "id") Long id);
    int delete(Long id);

}
