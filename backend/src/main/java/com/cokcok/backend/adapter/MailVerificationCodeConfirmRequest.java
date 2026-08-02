package com.cokcok.backend.adapter;

import com.cokcok.backend.application.request.MailVerificationCodeConfirmServiceRequest;
import lombok.Getter;

@Getter
public class MailVerificationCodeConfirmRequest {
    private final String email;
    private final String code;

    public MailVerificationCodeConfirmRequest(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public MailVerificationCodeConfirmServiceRequest toService() {
        return new MailVerificationCodeConfirmServiceRequest(this.email, this.code);
    }
}
