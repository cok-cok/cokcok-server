package com.cokcok.backend.adapter;

import com.cokcok.backend.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthSignUpResponse {
    private final String email;
    private final String nickname;

    @Builder(access = AccessLevel.PRIVATE)
    private AuthSignUpResponse(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    public static AuthSignUpResponse of(Member member) {
        return AuthSignUpResponse.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }
}
