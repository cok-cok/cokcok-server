package com.cokcok.backend.adapter;

import com.cokcok.backend.application.request.AuthLoginServiceRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthLoginRequest {
    private final String email;
    private final String password;

    @Builder(access = AccessLevel.PRIVATE)
    public AuthLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static AuthLoginRequest of(String email, String password) {
        return AuthLoginRequest.builder()
                .email(email)
                .password(password)
                .build();
    }

    public AuthLoginServiceRequest toServiceRequest() {
        return AuthLoginServiceRequest.of(this.email, this.password);
    }
}
