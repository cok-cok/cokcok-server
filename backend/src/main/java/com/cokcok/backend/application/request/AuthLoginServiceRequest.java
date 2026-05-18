package com.cokcok.backend.application.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthLoginServiceRequest {
    private final String email;
    private final String password;

    @Builder(access = AccessLevel.PRIVATE)
    private AuthLoginServiceRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static AuthLoginServiceRequest of(String email, String password) {
        return AuthLoginServiceRequest.builder()
                .email(email)
                .password(password)
                .build();
    }
}