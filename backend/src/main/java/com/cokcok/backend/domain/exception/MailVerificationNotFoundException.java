package com.cokcok.backend.domain.exception;

public class MailVerificationNotFoundException extends NotFoundException {
    public MailVerificationNotFoundException() {
        super("인증 코드가 올바르지 않습니다.");
    }
}
