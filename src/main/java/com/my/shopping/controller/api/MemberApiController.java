package com.my.shopping.controller.api;

import com.my.shopping.domain.member.Member;
import com.my.shopping.domain.member.dto.MemberCreateDto;
import com.my.shopping.domain.member.dto.MemberLoginDto;
import com.my.shopping.domain.member.dto.MemberUpdateDto;
import com.my.shopping.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<String> createMember(@RequestBody MemberCreateDto memberCreateDto) {
        Boolean isDuplicatedLoginId = memberService.isDuplicatedLoginId(memberCreateDto.getLoginId());

        if (!isDuplicatedLoginId) {
            memberService.insert(memberCreateDto);
            return ResponseEntity.ok("성공적으로 회원가입 되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("중복된 아이디입니다.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> createMember(@RequestBody MemberLoginDto memberLoginDto,
                                               HttpSession session) {
        Member member = memberService.login(memberLoginDto);
        if (member != null) {
            session.setAttribute("member", member);
            session.setAttribute("memberId", member.getId());
            return ResponseEntity.ok("성공적으로 로그인 되었습니다.");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("아이디 또는 비밀번호를 확인해주세요.");
    }

    @PostMapping("/members/{id}")
    public ResponseEntity<String> updateMember(@PathVariable(value = "id") Long id, @RequestBody MemberUpdateDto memberUpdateDto) {
        memberService.update(memberUpdateDto);
        return ResponseEntity.ok("성공적으로 정보수정 되었습니다.");
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable(value = "id") Long id, HttpSession session) {
        session.invalidate();
        memberService.delete(id);
        return ResponseEntity.ok("탈퇴 되었습니다.");
    }
}
