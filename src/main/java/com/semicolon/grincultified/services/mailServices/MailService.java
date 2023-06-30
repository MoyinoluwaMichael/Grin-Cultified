package com.semicolon.grincultified.services.mailServices;

import com.semicolon.grincultified.dtos.requests.SendMailRequest;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

public interface MailService {
    void sendMail(SendMailRequest sendMailRequest);
}
