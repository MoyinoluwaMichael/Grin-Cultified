package com.semicolon.grincultified.services.investmentservice;

import com.semicolon.grincultified.dtos.requests.InvestmentRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.DashboardStatistic;
import com.semicolon.grincultified.dtos.responses.InvestmentResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InvestmentService {
    ResponseEntity<InvestmentResponse> initiateInvestment(InvestmentRegistrationRequest investmentRegistrationRequest);
    ResponseEntity<List<InvestmentResponse>> findAll();
    ResponseEntity<List<InvestmentResponse>> findAllOngoingInvestmentsByEmail(String email);

    ResponseEntity<DashboardStatistic> getDashboardStatistics(String investorEmail);

    void deleteAll();
}
