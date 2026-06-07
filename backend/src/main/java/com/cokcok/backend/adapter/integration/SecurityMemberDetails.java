package com.cokcok.backend.adapter.integration;

import com.cokcok.backend.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class SecurityMemberDetails implements UserDetails {
    private static final String USER_DETAILS_USERNAME = "Anonymous";
    private static final String USER_DETAILS_PASSWORD = "Anonymous";

    private final String email;

    @Builder(access = AccessLevel.PRIVATE)
    private SecurityMemberDetails(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return USER_DETAILS_PASSWORD;
    }

    @Override
    public String getUsername() {
        return USER_DETAILS_USERNAME;
    }

    public static SecurityMemberDetails from(Member member) {
        return SecurityMemberDetails.builder()
                .email(member.getEmail())
                .build();
    }
}
