package com.semicolon.grincultified.services.investorService;

import com.semicolon.grincultified.data.models.*;
import com.semicolon.grincultified.data.repositories.InvestorRepo;
import com.semicolon.grincultified.dtos.requests.InvestorRegistrationRequest;
import com.semicolon.grincultified.dtos.requests.OtpVerificationRequest;
import com.semicolon.grincultified.dtos.requests.SendMailRequest;
import com.semicolon.grincultified.dtos.responses.*;
import com.semicolon.grincultified.exception.DuplicateInvestorException;
import com.semicolon.grincultified.exception.InvalidOtpException;
import com.semicolon.grincultified.exception.TemporaryInvestorDoesNotExistException;
import com.semicolon.grincultified.services.investmentservice.InvestmentService;
import com.semicolon.grincultified.services.mailService.MailService;
import com.semicolon.grincultified.services.otpService.OtpService;
import com.semicolon.grincultified.services.temporaryUserService.TemporaryUserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.semicolon.grincultified.data.models.Role.INVESTOR;
import static com.semicolon.grincultified.utilities.AppUtils.*;

@Service
@AllArgsConstructor
public class InvestorServiceImpl implements InvestorService {
    private final InvestorRepo investorRepo;
    private final ModelMapper modelMapper;
    private final TemporaryUserService temporaryUserService;
    private final MailService mailService;
    private final OtpService otpService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public ResponseEntity<GenericResponse<String>> initiateRegistration(InvestorRegistrationRequest investorRegistrationRequest) throws DuplicateInvestorException {
        Optional<Investor> foundInvestor = investorRepo.findByUser_EmailAddressContainingIgnoreCase(investorRegistrationRequest.getEmailAddress());
        if (foundInvestor.isPresent()) throw new DuplicateInvestorException(INVESTOR_ALREADY_EXIST);
        temporaryUserService.validateDuplicateTemporaryInvestor(investorRegistrationRequest.getEmailAddress());
        investorRegistrationRequest.setPassword(passwordEncoder.encode(investorRegistrationRequest.getPassword()));
        Otp otp = otpService.generateOtp();
        investorRegistrationRequest.setOtp(otp);
        temporaryUserService.addUserTemporarily(investorRegistrationRequest);
        sendOtp(investorRegistrationRequest);
        GenericResponse<String> genericResponse = new GenericResponse<>();
        genericResponse.setMessage(CHECK_YOUR_MAIL_FOR_YOUR_OTP);
        return ResponseEntity.ok().body(genericResponse);
    }

    @Override
    public ResponseEntity<InvestorResponse> confirmRegistration(OtpVerificationRequest otpVerificationRequest) throws TemporaryInvestorDoesNotExistException, InvalidOtpException {
        InvestorRegistrationRequest investorRegistrationRequest = otpService.verifyOtp(otpVerificationRequest);
        User user = modelMapper.map(investorRegistrationRequest, User.class);
        Address address = new Address();
        user.setAddress(address);
        user.setRoles(new HashSet<>());
        user.getRoles().add(INVESTOR);
        Investor investor = new Investor();
        investor.setUser(user);
        Investor savedInvestor = investorRepo.save(investor);
        temporaryUserService.deleteTemporaryInvestor(investorRegistrationRequest);
        return ResponseEntity.ok().body(map(savedInvestor));
    }

    private InvestorResponse map(Investor investor) {
        UserResponse userResponse = modelMapper.map(investor.getUser(), UserResponse.class);
        InvestorResponse investorResponse = modelMapper.map(investor, InvestorResponse.class);
        investorResponse.setUserResponse(userResponse);
        return investorResponse;
    }

    @Override
    public InvestorResponse findByEmail(String email) {
        Investor foundInvestor = investorRepo.findByUser_EmailAddressContainingIgnoreCase(email).get();
        return map(foundInvestor);
    }

    @Override
    public InvestorResponse findById(Long investorId) {
        return modelMapper.map(investorRepo.findById(investorId), InvestorResponse.class);
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

    @Override
    public ResponseEntity<List<InvestorResponse>> getAllInvestors() {
        List<Investor> investors = investorRepo.findAll();
        List<InvestorResponse> investorResponses =  investors.stream().map(this::map).collect(Collectors.toList());
        return ResponseEntity.ok().body(investorResponses);
    }

    @Override
    public void deleteAll() {
        investorRepo.deleteAll();
    }

}
