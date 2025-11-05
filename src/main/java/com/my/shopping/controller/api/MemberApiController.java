package com.my.shopping.controller.api;

import com.my.shopping.domain.member.dto.MemberCreateDto;
import com.my.shopping.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<String> createMember(@RequestBody MemberCreateDto memberCreateDto) {
        memberService.insert(memberCreateDto);
        return ResponseEntity.ok("성공적으로 회원가입 되었습니다.");
    }
}
