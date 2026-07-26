package com.cokcok.backend.domain;

import com.cokcok.backend.domain.exception.MailVerificationCodeMisMatchException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MailVerification {
    private Long id;
    private String email;
    private String code;
    private boolean verified;

    @Builder(access = AccessLevel.PRIVATE)
    private MailVerification(Long id, String email, String code, boolean verified) {
        this.id = id;
        this.email = email;
        this.code = code;
        this.verified = verified;
    }

    public static MailVerification create(String email, String code) {
        return MailVerification.builder()
                .email(email)
                .code(code)
                .verified(false)
                .build();
    }

    public static MailVerification of(Long id, String email, String code, boolean verified) {
        return MailVerification.builder()
                .id(id)
                .email(email)
                .code(code)
                .verified(verified)
                .build();
    }

    public void verify(String code) {
        verifyCode(code);
        completeVerification();
    }

    private void verifyCode(String submittedCode) {
        if(!this.code.equals(submittedCode)) {
            throw new MailVerificationCodeMisMatchException();
        }
    }

    private void completeVerification() {
        markAsVerified();
    }

    private void markAsVerified() {
        this.verified = true;
    }
}
