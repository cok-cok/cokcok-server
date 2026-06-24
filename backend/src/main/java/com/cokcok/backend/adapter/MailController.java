package com.cokcok.backend.adapter;

import com.cokcok.backend.application.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mail")
public class MailController {
    private final MailService mailService;

    @PostMapping("/auth/verification-code")
    public ResponseEntity<CommonSuccessResponse> sendVerificationCode(@RequestBody MailVerificationCodeRequest request) {
        mailService.sendVerificationCode(request.toService());
        return ResponseEntity.ok().body(new CommonSuccessResponse());
    }
}
