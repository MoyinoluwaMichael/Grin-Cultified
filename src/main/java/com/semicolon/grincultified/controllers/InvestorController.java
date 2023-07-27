package com.semicolon.grincultified.controllers;

import com.semicolon.grincultified.dtos.requests.InvestorRegistrationRequest;
import com.semicolon.grincultified.dtos.requests.CompleteRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.GenericResponse;
import com.semicolon.grincultified.dtos.responses.InvestorResponse;
import com.semicolon.grincultified.exception.*;
import com.semicolon.grincultified.services.investorService.InvestorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/semicolon/cultify/v1/api/investor")
public class InvestorController {
    private final InvestorService investorService;

    @PostMapping("/sendVerificationMail")
    public ResponseEntity<GenericResponse<String>> register(@RequestBody InvestorRegistrationRequest investorRegistrationRequest) throws DuplicateInvestorException, TemporaryInvestorDoesNotExistException, AdminAlreadyExistException, InvalidEmailException {
        return investorService.initiateRegistration(investorRegistrationRequest);
    }

    @PostMapping("/completeRegistration")
    public ResponseEntity<Map<String, Object>> confirmRegistration(@RequestBody CompleteRegistrationRequest completeRegistrationRequest) throws TemporaryInvestorDoesNotExistException, InvalidOtpException, InvestorRegistrationFailed {
        return investorService.confirmRegistration(completeRegistrationRequest);
    }

    @GetMapping("/getAllInvestors")
    public ResponseEntity<List<InvestorResponse>> getAllInvestors() {
        return investorService.getAllInvestors();
    }

}
