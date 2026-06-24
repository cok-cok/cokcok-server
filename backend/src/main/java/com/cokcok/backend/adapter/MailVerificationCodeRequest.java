package com.cokcok.backend.adapter;

import com.cokcok.backend.application.request.MailVerificationCodeServiceRequest;
import lombok.Getter;

@Getter
public class MailVerificationCodeRequest {
    private final String email;

    public MailVerificationCodeRequest(String email) {
        this.email = email;
    }

    public MailVerificationCodeServiceRequest toService() {
        return new MailVerificationCodeServiceRequest(this.email);
    }
}
