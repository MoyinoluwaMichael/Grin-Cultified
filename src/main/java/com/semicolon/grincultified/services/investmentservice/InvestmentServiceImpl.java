package com.semicolon.grincultified.services.investmentservice;

import com.semicolon.grincultified.data.models.Investment;
import com.semicolon.grincultified.data.models.InvestmentReturnType;
import com.semicolon.grincultified.data.repositories.InvestmentRepo;
import com.semicolon.grincultified.dtos.requests.InvestmentRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.InvestmentResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class InvestmentServiceImpl implements InvestmentService {

    private InvestmentRepo investmentRepo;

    @Override
    public InvestmentResponse initiateInvestment(InvestmentRegistrationRequest investmentRegistrationRequest) {
        Investment investment = new Investment();
        investmentRepo.save(investment);
        return null;
    }
}
