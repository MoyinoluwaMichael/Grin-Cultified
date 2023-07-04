package com.semicolon.grincultified.services.investmentservice;

import com.semicolon.grincultified.data.models.Investment;
import com.semicolon.grincultified.data.models.InvestmentReturnType;
import com.semicolon.grincultified.data.models.InvestmentStatus;
import com.semicolon.grincultified.data.models.Investor;
import com.semicolon.grincultified.data.repositories.InvestmentRepo;
import com.semicolon.grincultified.dtos.requests.InvestmentRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.InvestmentResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class InvestmentServiceImpl implements InvestmentService {

    private final InvestmentRepo investmentRepo;
    private final ModelMapper modelMapper;


    @Override
    public InvestmentResponse initiateInvestment(InvestmentRegistrationRequest investmentRegistrationRequest) {
        Investment investment = modelMapper.map(investmentRegistrationRequest, Investment.class);
        Investment invest = investmentRepo.save(investment);
        InvestmentResponse investmentNow = modelMapper.map(invest, InvestmentResponse.class);
        return investmentNow;
    }

    @Override
    public List<Investment> findAll() {
        return investmentRepo.findAll();
    }
}
