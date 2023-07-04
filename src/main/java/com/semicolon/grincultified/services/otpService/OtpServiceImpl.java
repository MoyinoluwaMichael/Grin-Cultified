package com.semicolon.grincultified.services.otpService;

import com.semicolon.grincultified.data.models.Otp;
import com.semicolon.grincultified.dtos.requests.InvestorRegistrationRequest;
import com.semicolon.grincultified.dtos.requests.OtpVerificationRequest;
import com.semicolon.grincultified.exception.TemporaryInvestorDoesNotExistException;
import com.semicolon.grincultified.services.temporaryUserService.TemporaryUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

import static com.semicolon.grincultified.utilities.AppUtils.TEMPORARY_INVESTOR_DOES_NOT_EXIST;

@Service
@AllArgsConstructor
public class OtpServiceImpl implements OtpService {
    public final TemporaryUserService temporaryUserService;


    @Override
    public InvestorRegistrationRequest verifyOtp(OtpVerificationRequest otpVerificationRequest) throws TemporaryInvestorDoesNotExistException {
        InvestorRegistrationRequest investorRegistrationRequest = temporaryUserService.findByEmailAddress(otpVerificationRequest.getEmailAddress());
        if (investorRegistrationRequest == null) throw new TemporaryInvestorDoesNotExistException(TEMPORARY_INVESTOR_DOES_NOT_EXIST);
        return investorRegistrationRequest;
    }

    @Override
    public Otp generateOtp() {
        SecureRandom secureRandom = new SecureRandom();
        int otpValue = secureRandom.nextInt(999999) + 100000;
        Otp otp = Otp.builder()
                .otpToken(String.valueOf(otpValue))
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(60))
                .build();
        return otp;
    }
}
