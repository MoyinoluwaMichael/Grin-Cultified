package com.semicolon.grincultified.services.investmentservice;

import com.semicolon.grincultified.data.models.Investment;
import com.semicolon.grincultified.data.models.InvestmentStatus;
import com.semicolon.grincultified.dtos.requests.InvestmentRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.InvestmentResponse;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

public interface InvestmentService {
    ResponseEntity<InvestmentResponse> initiateInvestment(InvestmentRegistrationRequest investmentRegistrationRequest);
    ResponseEntity<List<InvestmentResponse>> findAll();
    ResponseEntity<List<InvestmentResponse>> findAllOngoingInvestmentsByEmail(String email);

    void deleteAll();

    ResponseEntity<List<InvestmentResponse>> getAllOngoingProjectInvestments();

    List<Investment> findAllByInvestorIdAndStatusOrStatus(Long id, InvestmentStatus status, InvestmentStatus status2);

    Long countAllByFarmProjectId(Long farmProjectId);

    BigDecimal calculateAllInvestmentsAmountByFarmProjectId(Long farmProjectId);
}
