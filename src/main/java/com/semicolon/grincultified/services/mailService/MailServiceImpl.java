package com.semicolon.grincultified.services.mailService;

import com.semicolon.grincultified.dtos.requests.InvestorRegistrationRequest;
import com.semicolon.grincultified.dtos.requests.SendMailRequest;
import com.semicolon.grincultified.exception.InvalidEmailException;
import lombok.Builder;
import org.modelmapper.ModelMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static com.semicolon.grincultified.utilities.AppUtils.*;
import static com.semicolon.grincultified.utilities.AppUtils.INVALID_EMAIL;

@Builder
@Service
public class MailServiceImpl implements MailService{
    private final JavaMailSender mailSender;
    private final ModelMapper modelMapper;

    @Override
    public void sendMail(SendMailRequest sendMailRequest) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(sendMailRequest.getTo());
        mailMessage.setFrom(sendMailRequest.getFrom());
        mailMessage.setSubject(sendMailRequest.getSubject());
        mailMessage.setText(sendMailRequest.getText());
        mailSender.send(mailMessage);
    }

    @Override
    public String sendEmailVerificationMail(InvestorRegistrationRequest investorRegistrationRequest, String invitationLink) throws InvalidEmailException {
        SimpleMailMessage sendMailRequest = new SimpleMailMessage();
        sendMailRequest.setFrom(SYSTEM_MAIL);
        sendMailRequest.setTo(investorRegistrationRequest.getEmailAddress());
        sendMailRequest.setSubject(EMAIL_VERIFICATION);
        sendMailRequest.setText(String.format(INVESTOR_EMAIL_VERIFICATION_MAIL_TEMPLATE, investorRegistrationRequest.getFirstName(), invitationLink));
        try {
            mailSender.send(sendMailRequest);
        } catch (Exception e) {
            throw new InvalidEmailException(INVALID_EMAIL);
        }
        return MAIL_SENT_SUCCESSFULLY;
    }
}
