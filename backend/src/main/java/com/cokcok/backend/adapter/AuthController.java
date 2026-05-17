package com.cokcok.backend.adapter;

import com.cokcok.backend.application.AuthService;
import com.cokcok.backend.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthLoginResponse> login(@RequestBody AuthLoginRequest request) {
        Member member = authService.login(request.toServiceRequest());
        return ResponseEntity.ok().body(AuthLoginResponse.from(member, "accessToken"));
    }
}