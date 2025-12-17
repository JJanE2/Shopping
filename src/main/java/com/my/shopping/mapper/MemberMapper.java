package com.my.shopping.mapper;

import com.my.shopping.domain.member.Member;
import com.my.shopping.domain.member.dto.MemberCreateDto;
import com.my.shopping.domain.member.dto.MemberUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    void insert(MemberCreateDto memberCreateDto);
    Member findById(Long id);
    Member findByLoginId(@Param(value = "loginId") String loginId);
    int update(MemberUpdateDto memberUpdateDto);
    int delete(Long id);

    Member findByKakaoId(String kakoId);
}
