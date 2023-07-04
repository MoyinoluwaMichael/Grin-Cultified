package com.semicolon.grincultified.controllers;

import com.semicolon.grincultified.dtos.requests.InvestmentRegistrationRequest;
import com.semicolon.grincultified.services.investmentservice.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class InvestmentController {
    @Autowired
    private InvestmentService investmentService;

    public ResponseEntity<?> initiateInvestment(@RequestBody InvestmentRegistrationRequest investmentRegistrationRequest){
          return new  ResponseEntity<>(investmentService.initiateInvestment(investmentRegistrationRequest), HttpStatus.CREATED);
    }

}
