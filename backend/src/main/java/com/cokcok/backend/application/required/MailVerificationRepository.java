package com.cokcok.backend.application.required;

import com.cokcok.backend.domain.MailVerification;

public interface MailVerificationRepository {

    MailVerification findByEmail(String email);

    MailVerification save(MailVerification mailVerification);
}
