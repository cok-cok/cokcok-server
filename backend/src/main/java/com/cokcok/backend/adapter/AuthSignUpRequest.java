package com.cokcok.backend.adapter;

import com.cokcok.backend.application.request.AuthSignUpServiceRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthSignUpRequest {
    private final String email;
    private final String password;
    private final String nickname;

    @Builder(access = AccessLevel.PRIVATE)
    public AuthSignUpRequest(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public static AuthSignUpRequest of(String email, String password, String nickname) {
        return AuthSignUpRequest.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();
    }

    public AuthSignUpServiceRequest toServiceRequest() {
        return AuthSignUpServiceRequest.of(this.email, this.password, this.nickname);
    }
}
