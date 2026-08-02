package com.cokcok.backend.domain.exception;

public class MailVerificationCodeExpiredException extends BusinessException {
    public MailVerificationCodeExpiredException() {
        super("인증 코드가 만료되었습니다. 다시 인증해주세요.");
    }
}
