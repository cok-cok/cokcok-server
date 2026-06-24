package com.cokcok.backend.application;

import com.cokcok.backend.adapter.utils.GeneratorUtils;
import com.cokcok.backend.application.request.MailVerificationCodeServiceRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class MailService {
    private static final String MAIL_MESSAGE_ENCODING = "UTF-8";
    private static final String MAIL_MESSAGE_SUBJECT = "[콕콕] 회원 가입을 위한 인증 코드가 도착했습니다.";
    private static final int MAIL_MESSAGE_CODE_LENGTH = 6;
    private static final String TEMPLATE_CODE_VARIABLE = "code";
    private static final String MAIL_MESSAGE_TEMPLATE = "SignUpVerificationCodeMail";

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public void sendVerificationCode(MailVerificationCodeServiceRequest request) {

        String verificationCode = GeneratorUtils.generateCode(MAIL_MESSAGE_CODE_LENGTH, 0, 10);
        String SignUpVerificationCodeMailHtml = processVerificationCodeMailHtml(verificationCode);

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MAIL_MESSAGE_ENCODING);
            helper.setTo(request.getEmail());
            helper.setSubject(MAIL_MESSAGE_SUBJECT);
            helper.setText(SignUpVerificationCodeMailHtml, true);
            mailSender.send(message);
        } catch (MessagingException exception) {
            System.out.println("실패 처리 코드 추가 예정");
        }
    }

    private String processVerificationCodeMailHtml(String verificationCode) {
        Context context = new Context();
        context.setVariable(TEMPLATE_CODE_VARIABLE, verificationCode);
        return templateEngine.process(MAIL_MESSAGE_TEMPLATE, context);
    }
}