package com.cokcok.backend.adapter;

import com.cokcok.backend.domain.MailVerification;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MailVerificationConfirmResponse {
    private final String email;
    private final String verificationCode;
    private final boolean verified;

    @Builder(access = AccessLevel.PRIVATE)
    private MailVerificationConfirmResponse(String email, String verificationCode, boolean verified) {
        this.email = email;
        this.verificationCode = verificationCode;
        this.verified = verified;
    }

    public static MailVerificationConfirmResponse of(MailVerification mailVerification) {
        return MailVerificationConfirmResponse.builder()
                .email(mailVerification.getEmail())
                .verificationCode(mailVerification.getCode())
                .verified(mailVerification.isVerified())
                .build();
    }
}
