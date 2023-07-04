package com.semicolon.grincultified.services.mailServices;

import com.semicolon.grincultified.dtos.requests.SendMailRequest;
import lombok.Builder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Builder
@Service
public class MailServiceImpl implements MailService{
    private final JavaMailSender mailSender;
    private final ModelMapper modelMapper;

    @Override
    public void sendMail(SendMailRequest sendMailRequest) {
        SimpleMailMessage mailMessage = modelMapper.map(sendMailRequest, SimpleMailMessage.class);
        mailSender.send(mailMessage);
    }
}
