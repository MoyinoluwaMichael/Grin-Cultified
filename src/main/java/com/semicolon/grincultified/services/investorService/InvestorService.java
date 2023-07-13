package com.semicolon.grincultified.services.investorService;

import com.semicolon.grincultified.dtos.requests.InvestorRegistrationRequest;
import com.semicolon.grincultified.dtos.requests.OtpVerificationRequest;
import com.semicolon.grincultified.dtos.responses.DashboardStatistic;
import com.semicolon.grincultified.dtos.responses.GenericResponse;
import com.semicolon.grincultified.dtos.responses.InvestorResponse;
import com.semicolon.grincultified.exception.DuplicateInvestorException;
import com.semicolon.grincultified.exception.InvalidOtpException;
import com.semicolon.grincultified.exception.TemporaryInvestorDoesNotExistException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InvestorService {
    ResponseEntity<GenericResponse<String>> initiateRegistration(InvestorRegistrationRequest investorRegistrationRequest) throws DuplicateInvestorException, TemporaryInvestorDoesNotExistException;
    ResponseEntity<InvestorResponse> confirmRegistration(OtpVerificationRequest otpVerificationRequest) throws TemporaryInvestorDoesNotExistException, InvalidOtpException;

    InvestorResponse findByEmail(String email);

    InvestorResponse findById(Long investorId);

    ResponseEntity<List<InvestorResponse>> getAllInvestors();

    void deleteAll();
}
