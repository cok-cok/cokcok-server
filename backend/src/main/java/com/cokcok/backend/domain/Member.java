package com.cokcok.backend.domain;

import com.cokcok.backend.domain.exception.LoginFailedException;
import com.cokcok.backend.domain.exception.PasswordMissMatchException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {
    private Long id;
    private String email;
    private String password;
    private String nickname;

    @Builder(access = AccessLevel.PRIVATE)
    private Member(Long id, String email, String password, String nickname) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public static Member create(String email, String password, String passwordConfirm, String nickname) {
        verifyPasswordMatch(password, passwordConfirm);
        return Member.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();
    }

    public static Member of(Long id, String email, String password, String nickname) {
        return Member.builder()
                .id(id)
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();
    }

    public void login(String password) {
        verifyPassword(password);
    }

    private void verifyPassword(String password) {
        if(!this.getPassword().equals(password)) {
            throw new LoginFailedException();
        }
    }

    private static void verifyPasswordMatch(String password, String passwordConfirm) {
        if(!password.equals(passwordConfirm)) {
            throw new PasswordMissMatchException();
        }
    }
}