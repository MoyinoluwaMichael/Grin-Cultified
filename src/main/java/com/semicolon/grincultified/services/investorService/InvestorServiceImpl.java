package com.semicolon.grincultified.services.investorService;

import com.semicolon.grincultified.data.models.Address;
import com.semicolon.grincultified.data.models.Investor;
import com.semicolon.grincultified.data.models.Otp;
import com.semicolon.grincultified.data.models.User;
import com.semicolon.grincultified.data.repositories.InvestorRepo;
import com.semicolon.grincultified.dtos.requests.InvestorRegistrationRequest;
import com.semicolon.grincultified.dtos.requests.OtpVerificationRequest;
import com.semicolon.grincultified.dtos.requests.SendMailRequest;
import com.semicolon.grincultified.dtos.responses.InvestorRegistrationResponse;
import com.semicolon.grincultified.exception.DuplicateInvestorException;
import com.semicolon.grincultified.exception.TemporaryInvestorDoesNotExistException;
import com.semicolon.grincultified.services.mailService.MailService;
import com.semicolon.grincultified.services.otpService.OtpService;
import com.semicolon.grincultified.services.temporaryUserService.TemporaryUserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.semicolon.grincultified.utilities.AppUtils.*;

@Service
@AllArgsConstructor
public class InvestorServiceImpl implements InvestorService {
    private final InvestorRepo investorRepo;
    private final ModelMapper modelMapper;
    private final TemporaryUserService temporaryUserService;
    private final MailService mailService;
    private final OtpService otpService;

    @Override
    public ResponseEntity<InvestorRegistrationResponse> initiateRegistration(InvestorRegistrationRequest investorRegistrationRequest) throws DuplicateInvestorException {
        Optional<Investor> foundInvestor = investorRepo.findByUser_EmailAddressContainingIgnoreCase(investorRegistrationRequest.getEmailAddress());
        if (foundInvestor.isPresent()) throw new DuplicateInvestorException(EMAIL_ALREADY_EXIST);
        Otp otp = otpService.generateOtp();
        investorRegistrationRequest.setOtp(otp);
        temporaryUserService.addUserTemporarily(investorRegistrationRequest);
        sendOtp(investorRegistrationRequest);
        return ResponseEntity.ok().body(InvestorRegistrationResponse
                .builder()
                .message(CHECK_YOUR_MAIL_FOR_YOUR_OTP)
                .build());
    }

    @Override
    public InvestorRegistrationResponse confirmRegistration(OtpVerificationRequest otpVerificationRequest) throws TemporaryInvestorDoesNotExistException {
        InvestorRegistrationRequest investorRegistrationRequest = otpService.verifyOtp(otpVerificationRequest);
        User user = modelMapper.map(investorRegistrationRequest, User.class);
        Address address = new Address();
        user.setAddress(address);
        Investor investor = new Investor();
        investor.setUser(user);
        investorRepo.save(investor);
        temporaryUserService.deleteTemporaryInvestor(investorRegistrationRequest);
        return InvestorRegistrationResponse.builder()
                .message(REGISTERED_SUCCESSFULLY)
                .build();
    }



    private String sendOtp(InvestorRegistrationRequest investorRegistrationRequest) {
        SendMailRequest sendMailRequest = new SendMailRequest();
        sendMailRequest.setFrom(SYSTEM_MAIL);
        sendMailRequest.setTo(investorRegistrationRequest.getEmailAddress());
        sendMailRequest.setSubject(REGISTRATION_OTP);
        String text = String.format(OTP_TOKEN, investorRegistrationRequest.getOtp().getOtpToken());
        sendMailRequest.setText(text);
        mailService.sendMail(sendMailRequest);
        return sendMailRequest.getText();
    }
}
