package com.cokcok.backend.adapter;

import com.cokcok.backend.adapter.integration.JwtProvider;
import com.cokcok.backend.application.AuthService;
import com.cokcok.backend.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<AuthLoginResponse> login(@RequestBody AuthLoginRequest request) {
        Member member = authService.login(request.toServiceRequest());
        MemberGetResponse memberGetResponse = MemberGetResponse.from(member);
        String accessToken = jwtProvider.createToken(member.getEmail(), member.getNickname());
        AuthLoginResponse response = AuthLoginResponse.of(memberGetResponse, accessToken);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthSignUpResponse> signUp(@RequestBody AuthSignUpRequest request) {
        Member member = authService.signUp(request.toServiceRequest());
        AuthSignUpResponse response = AuthSignUpResponse.of(member);
        return ResponseEntity.created(URI.create("/api/auth/signup")).body(response);
    }
}