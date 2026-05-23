package com.cokcok.backend.adapter;

import com.cokcok.backend.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberGetResponse {
    private final Long id;
    private final String nickname;

    @Builder(access = AccessLevel.PRIVATE)
    private MemberGetResponse(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public static MemberGetResponse from(Member member) {
        return MemberGetResponse.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .build();
    }
}
