package com.cokcok.backend.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MailVerificationJpaRepositorySupport extends JpaRepository<MailVerificationEntity, Long> {

    Optional<MailVerificationEntity> findByEmail(String email);
}
