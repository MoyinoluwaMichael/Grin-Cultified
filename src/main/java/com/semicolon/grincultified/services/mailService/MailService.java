package com.semicolon.grincultified.services.mailService;

import com.semicolon.grincultified.dtos.requests.SendMailRequest;

public interface MailService {
    void sendMail(SendMailRequest sendMailRequest);
}
