package com.cokcok.backend.domain.exception;

public class LoginFailedException extends AuthException {
    public LoginFailedException() {
        super("이메일 또는 비밀번호가 올바르지 않습니다.");
    }
}


