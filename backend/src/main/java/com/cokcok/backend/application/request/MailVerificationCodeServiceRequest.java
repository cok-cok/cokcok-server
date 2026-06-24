package com.cokcok.backend.application.request;

import lombok.Getter;

@Getter
public class MailVerificationCodeServiceRequest {
    private final String email;

    public MailVerificationCodeServiceRequest(String email) {
        this.email = email;
    }
}
