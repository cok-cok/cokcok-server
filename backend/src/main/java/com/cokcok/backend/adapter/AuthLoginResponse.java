package com.cokcok.backend.adapter;

import com.cokcok.backend.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthLoginResponse {
    private final Long id;
    private final String nickname;
    private final String accessToken;

    @Builder(access = AccessLevel.PRIVATE)
    public AuthLoginResponse(Long id, String nickname, String accessToken) {
        this.accessToken = accessToken;
        this.id = id;
        this.nickname = nickname;
    }

    public static AuthLoginResponse from(Member member, String accessToken) {
        return AuthLoginResponse.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .accessToken(accessToken)
                .build();
    }
}