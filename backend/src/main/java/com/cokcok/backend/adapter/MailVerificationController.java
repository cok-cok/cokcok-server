package com.cokcok.backend.adapter;

import com.cokcok.backend.application.MailVerificationService;
import com.cokcok.backend.domain.MailVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mail")
public class MailVerificationController {
    private final MailVerificationService mailVerificationService;

    @PostMapping("/auth/verification-code")
    public ResponseEntity<CommonSuccessResponse> sendVerificationCode(@RequestBody MailVerificationCodeRequest request) {
        mailVerificationService.sendVerificationCode(request.toService());
        return ResponseEntity.ok().body(new CommonSuccessResponse());
    }

    @PostMapping("/auth/verification-code/confirm")
    public ResponseEntity<MailVerificationConfirmResponse> confirmVerificationCode(@RequestBody MailVerificationCodeConfirmRequest request) {
        MailVerification mailVerification = mailVerificationService.confirmVerificationCode(request.toService());
        MailVerificationConfirmResponse response = MailVerificationConfirmResponse.of(mailVerification);
        return ResponseEntity.ok().body(response);
    }
}
