package com.semicolon.grincultified.services.otpService;

import com.semicolon.grincultified.data.models.Otp;
import com.semicolon.grincultified.dtos.requests.InvestorRegistrationRequest;
import com.semicolon.grincultified.dtos.requests.OtpVerificationRequest;
import com.semicolon.grincultified.exception.InvalidOtpException;
import com.semicolon.grincultified.exception.TemporaryInvestorDoesNotExistException;
import com.semicolon.grincultified.services.temporaryUserService.TemporaryUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

import static com.semicolon.grincultified.utilities.AppUtils.TEMPORARY_INVESTOR_DOES_NOT_EXIST;
import static com.semicolon.grincultified.utilities.AppUtils.YOU_HAVE_ENTERED_AN_INVALID_OTP;

@Service
@AllArgsConstructor
public class OtpServiceImpl implements OtpService {
    public final TemporaryUserService temporaryUserService;


    @Override
    public InvestorRegistrationRequest verifyOtp(OtpVerificationRequest otpVerificationRequest) throws TemporaryInvestorDoesNotExistException, InvalidOtpException {
        InvestorRegistrationRequest investorRegistrationRequest = temporaryUserService.findByEmail(otpVerificationRequest.getEmailAddress());
        if (investorRegistrationRequest == null) throw new TemporaryInvestorDoesNotExistException(TEMPORARY_INVESTOR_DOES_NOT_EXIST);
        boolean otpIsCorrect = otpVerificationRequest.getOtp().equals(investorRegistrationRequest.getOtp().getOtpToken());
        if (!otpIsCorrect) throw new InvalidOtpException(YOU_HAVE_ENTERED_AN_INVALID_OTP);
        return investorRegistrationRequest;
    }

    @Override
    public Otp generateOtp() {
        SecureRandom secureRandom = new SecureRandom();
        int otpValue = secureRandom.nextInt(100000,1000000);
        Otp otp = Otp.builder()
                .otpToken(String.valueOf(otpValue))
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(60))
                .build();
        return otp;
    }
}
