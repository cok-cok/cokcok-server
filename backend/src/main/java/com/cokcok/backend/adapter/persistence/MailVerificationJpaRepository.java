package com.cokcok.backend.adapter.persistence;

import com.cokcok.backend.application.required.MailVerificationRepository;
import com.cokcok.backend.domain.MailVerification;
import com.cokcok.backend.domain.exception.MailVerificationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MailVerificationJpaRepository implements MailVerificationRepository {
    private final MailVerificationJpaRepositorySupport mailVerificationJpaRepositorySupport;

    @Override
    public MailVerification findByEmail(String email) {
        return mailVerificationJpaRepositorySupport.findByEmail(email)
                .orElseThrow(MailVerificationNotFoundException::new)
                .toModel();
    }

    @Override
    public MailVerification save(MailVerification mailVerification) {
        return mailVerificationJpaRepositorySupport
                .save(MailVerificationEntity.from(mailVerification))
                .toModel();
    }
}