package com.semicolon.grincultified.services.otpService;

import com.semicolon.grincultified.data.models.Otp;
import com.semicolon.grincultified.dtos.requests.InvestorRegistrationRequest;
import com.semicolon.grincultified.dtos.requests.OtpVerificationRequest;
import com.semicolon.grincultified.exception.TemporaryInvestorDoesNotExistException;

public interface OtpService {
    InvestorRegistrationRequest verifyOtp(OtpVerificationRequest otpVerificationRequest) throws TemporaryInvestorDoesNotExistException;
    Otp generateOtp();
}
