package com.semicolon.grincultified.controllers;

import com.semicolon.grincultified.data.models.Investment;
import com.semicolon.grincultified.dtos.requests.InvestmentRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.InvestmentResponse;
import com.semicolon.grincultified.services.investmentservice.InvestmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/semicolon/cultify/v1/api/investment")
public class InvestmentController {

    private final InvestmentService investmentService;

    @PostMapping("/initiateInvestment")
    public ResponseEntity<InvestmentResponse> initiateInvestment(@RequestBody InvestmentRegistrationRequest investmentRegistrationRequest){
          return investmentService.initiateInvestment(investmentRegistrationRequest);
    }

    @GetMapping("/getAllInvestments")
    public ResponseEntity<List<InvestmentResponse>> findAll() {
        return investmentService.findAll();
    }

    @GetMapping("/getAllInvestmentsByEmail/{email}")
    public ResponseEntity<List<InvestmentResponse>> findAllByEmail(@PathVariable String email) {
        return investmentService.findInvestmentByEmail(email);
    }


}
