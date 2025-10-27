package com.my.shopping.service;

import com.my.shopping.domain.member.Member;
import com.my.shopping.mapper.MemberMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MemberServiceImpl memberService;

    @Test
    void 생성및조회_테스트() {

        Member member = new Member(null, "nickname", "loginId", "password");

        memberService.insert(member);

        Member findMember = memberService.findById(1L);
        Long findMemberId = findMember.getId();

        assertThat(findMemberId).isEqualTo(1L);
    }

    @Test
    void 삭제_테스트 () {
        //given
        Member member = new Member(null, "nickname", "loginId", "password");
        memberService.insert(member);

        //when
        memberService.delete(1L);
        Member deletedMember = memberService.findById(1L);

        //then
        assertThat(deletedMember).isNull();
    }

    @Test
    void 수정_테스트 () {
        //given
        Member member = new Member(null, "nickname", "loginId", "password");
        memberService.insert(member);
        String newName =  "newName";
        String newPw =  "newPw";
        Long memberId = 1L;

        //when
        memberService.update(newName, newPw, memberId);

        Member updatedMember = memberService.findById(1L);

        //then
        assertThat(updatedMember.getNickname()).isEqualTo(newName);
        assertThat(updatedMember.getPassword()).isEqualTo(newPw);
        assertThat(updatedMember.getId()).isEqualTo(memberId);
    }

}