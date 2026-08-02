package com.cokcok.backend.domain.exception;

public class MailVerificationCodeMisMatchException extends AuthException {
    public MailVerificationCodeMisMatchException() {
        super("인증 코드가 올바르지 않습니다.");
    }
}
