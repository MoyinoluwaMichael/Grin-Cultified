package com.semicolon.grincultified.controllers;

import com.semicolon.grincultified.dtos.requests.InvestorRegistrationRequest;
import com.semicolon.grincultified.dtos.requests.OtpVerificationRequest;
import com.semicolon.grincultified.dtos.responses.DashboardStatistic;
import com.semicolon.grincultified.dtos.responses.GenericResponse;
import com.semicolon.grincultified.dtos.responses.InvestorResponse;
import com.semicolon.grincultified.exception.DuplicateInvestorException;
import com.semicolon.grincultified.exception.InvalidOtpException;
import com.semicolon.grincultified.exception.TemporaryInvestorDoesNotExistException;
import com.semicolon.grincultified.services.investorService.InvestorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/semicolon/cultify/v1/api/investor")
public class InvestorController {
    private final InvestorService investorService;

    @GetMapping("/test")
    public String test() {
        return "Hello world";
    }

    @PostMapping("/registration")
    public ResponseEntity<GenericResponse<String>> register(@RequestBody InvestorRegistrationRequest investorRegistrationRequest) throws DuplicateInvestorException, TemporaryInvestorDoesNotExistException {
        return investorService.initiateRegistration(investorRegistrationRequest);
    }

    @PostMapping("/confirmRegistration")
    public ResponseEntity<InvestorResponse> confirmRegistration(@RequestBody OtpVerificationRequest otpVerificationRequest) throws TemporaryInvestorDoesNotExistException, InvalidOtpException {
        return investorService.confirmRegistration(otpVerificationRequest);
    }

    @GetMapping("/getAllInvestors")
    public ResponseEntity<List<InvestorResponse>> getAllInvestors() {
        return investorService.getAllInvestors();
    }

}
