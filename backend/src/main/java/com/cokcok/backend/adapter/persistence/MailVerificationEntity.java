package com.cokcok.backend.adapter.persistence;

import com.cokcok.backend.domain.MailVerification;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "mail_verifications")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MailVerificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "verified", nullable = false)
    private boolean verified;

    @Builder(access = AccessLevel.PRIVATE)
    private MailVerificationEntity(Long id, String email, String code, boolean verified) {
        this.id = id;
        this.email = email;
        this.code = code;
        this.verified = verified;
    }

    public static MailVerificationEntity from(MailVerification mailVerification) {
        return MailVerificationEntity.builder()
                .id(mailVerification.getId())
                .email(mailVerification.getEmail())
                .code(mailVerification.getCode())
                .verified(mailVerification.isVerified())
                .build();
    }

    public MailVerification toModel() {
        return MailVerification.of(this.id, this.email, this.code, this.verified);
    }
}
