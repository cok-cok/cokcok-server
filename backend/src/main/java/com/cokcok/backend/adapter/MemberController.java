package com.cokcok.backend.adapter;

import com.cokcok.backend.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/check-nickname")
    public ResponseEntity<MemberNicknameAvailableResponse> checkNicknameDuplication(@RequestParam("nickname") String nickname) {
        memberService.checkNicknameDuplication(nickname);
        return ResponseEntity.ok().body(new MemberNicknameAvailableResponse(true));
    }
}