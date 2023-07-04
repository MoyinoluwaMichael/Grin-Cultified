package com.semicolon.grincultified.controllers;

import com.semicolon.grincultified.dtos.requests.InvestorRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.InvestorRegistrationResponse;
import com.semicolon.grincultified.exception.DuplicateInvestorException;
import com.semicolon.grincultified.services.investorService.InvestorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<InvestorRegistrationResponse> register(InvestorRegistrationRequest investorRegistrationRequest) throws DuplicateInvestorException {
        return investorService.initiateRegistration(investorRegistrationRequest);
    }
}
