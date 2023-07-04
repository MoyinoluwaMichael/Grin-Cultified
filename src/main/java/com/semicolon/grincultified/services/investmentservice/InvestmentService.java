package com.semicolon.grincultified.services.investmentservice;

import com.semicolon.grincultified.dtos.requests.InvestmentRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.InvestmentResponse;

public interface InvestmentService {
    InvestmentResponse initiateInvestment(InvestmentRegistrationRequest investmentRegistrationRequest);
}
