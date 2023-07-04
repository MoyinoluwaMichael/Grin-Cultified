package com.semicolon.grincultified.services.investmentservice;

import com.semicolon.grincultified.data.models.Investment;
import com.semicolon.grincultified.dtos.requests.InvestmentRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.InvestmentResponse;

import java.util.List;

public interface InvestmentService {
    InvestmentResponse initiateInvestment(InvestmentRegistrationRequest investmentRegistrationRequest);
    List<Investment> findAll();
}
