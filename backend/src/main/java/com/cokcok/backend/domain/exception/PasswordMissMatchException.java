package com.cokcok.backend.domain.exception;

public class PasswordMissMatchException extends AuthException {
    public PasswordMissMatchException() {
        super("비밀번호가 일치하지 않습니다.");
    }
}
