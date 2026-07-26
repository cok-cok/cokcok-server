package com.cokcok.backend.domain;

import com.cokcok.backend.domain.exception.MailVerificationCodeExpiredException;
import com.cokcok.backend.domain.exception.MailVerificationCodeMisMatchException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MailVerification {
    private static final int EXPIRATION_DURATION = 3;

    private Long id;
    private String email;
    private String code;
    private boolean verified;
    private LocalDateTime expiresAt;

    @Builder(access = AccessLevel.PRIVATE)
    private MailVerification(Long id, String email, String code, boolean verified, LocalDateTime expiresAt) {
        this.id = id;
        this.email = email;
        this.code = code;
        this.verified = verified;
        this.expiresAt = expiresAt;
    }

    public static MailVerification create(String email, String code) {
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(EXPIRATION_DURATION);
        System.out.println(expiresAt);
        return MailVerification.builder()
                .email(email)
                .code(code)
                .verified(false)
                .expiresAt(expiresAt)
                .build();
    }

    public static MailVerification of(Long id, String email, String code, boolean verified, LocalDateTime expiresAt) {
        return MailVerification.builder()
                .id(id)
                .email(email)
                .code(code)
                .verified(verified)
                .expiresAt(expiresAt)
                .build();
    }

    public void verify(String code) {
        verifyExpiration();
        verifyCode(code);
        completeVerification();
    }

    private void verifyExpiration() {
        if(LocalDateTime.now().isAfter(this.expiresAt)) {
            throw new MailVerificationCodeExpiredException();
        }
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
