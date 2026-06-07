package com.cokcok.backend.application.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthSignUpServiceRequest {
    private final String email;
    private final String password;
    private final String passwordConfirm;
    private final String nickname;

    @Builder(access = AccessLevel.PRIVATE)
    private AuthSignUpServiceRequest(String email, String password, String passwordConfirm, String nickname) {
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.nickname = nickname;
    }

    public static AuthSignUpServiceRequest of(String email, String password, String passwordConfirm, String nickname) {
        return AuthSignUpServiceRequest.builder()
                .email(email)
                .password(password)
                .passwordConfirm(passwordConfirm)
                .nickname(nickname)
                .build();
    }
}
