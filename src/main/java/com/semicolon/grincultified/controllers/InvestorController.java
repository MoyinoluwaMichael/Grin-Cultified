package com.semicolon.grincultified.controllers;

import com.semicolon.grincultified.dtos.requests.InvestorRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.GenericResponse;
import com.semicolon.grincultified.dtos.responses.InvestorResponse;
import com.semicolon.grincultified.exception.DuplicateInvestorException;
import com.semicolon.grincultified.services.investorService.InvestorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/semicolon/cultify/v1/api/investor")
public class InvestorController {
    private final InvestorService investorService;

    @GetMapping("/test")
    public String test(){
        return "Hello world";
    }

    @PostMapping("/registration")
    public ResponseEntity<GenericResponse<String>> register(@RequestBody InvestorRegistrationRequest investorRegistrationRequest) throws DuplicateInvestorException {
        return investorService.initiateRegistration(investorRegistrationRequest);
    }

    @GetMapping("/getAllInvestors")
    public ResponseEntity<InvestorResponse> getAllInvestors(){
        return null;
    }
}
