package com.cokcok.backend.application.request;

import lombok.Getter;

@Getter
public class MailVerificationCodeConfirmServiceRequest {
    private final String email;
    private final String code;

    public MailVerificationCodeConfirmServiceRequest(String email, String code) {
        this.email = email;
        this.code = code;
    }
}
