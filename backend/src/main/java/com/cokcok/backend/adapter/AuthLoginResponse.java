package com.cokcok.backend.adapter;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthLoginResponse {
    private final MemberGetResponse user;
    private final String accessToken;

    @Builder(access = AccessLevel.PRIVATE)
    public AuthLoginResponse(MemberGetResponse user, String accessToken) {
        this.user = user;
        this.accessToken = accessToken;
    }

    public static AuthLoginResponse of(MemberGetResponse user, String accessToken) {
        return AuthLoginResponse.builder()
                .user(user)
                .accessToken(accessToken)
                .build();
    }
}