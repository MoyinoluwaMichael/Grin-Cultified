package com.semicolon.grincultified.services.mailService;

import com.semicolon.grincultified.dtos.requests.InvestorRegistrationRequest;
import com.semicolon.grincultified.dtos.requests.SendMailRequest;
import com.semicolon.grincultified.exception.InvalidEmailException;

public interface MailService {
    void sendMail(SendMailRequest sendMailRequest);

    String sendEmailVerificationMail(InvestorRegistrationRequest investorRegistrationRequest, String invitationLink) throws InvalidEmailException;
}
