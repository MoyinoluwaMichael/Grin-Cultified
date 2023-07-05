package com.semicolon.grincultified.services.investmentservice;

import com.semicolon.grincultified.data.models.Investment;
import com.semicolon.grincultified.data.models.InvestmentReturnType;
import com.semicolon.grincultified.data.models.InvestmentStatus;
import com.semicolon.grincultified.data.models.Investor;
import com.semicolon.grincultified.data.repositories.InvestmentRepo;
import com.semicolon.grincultified.dtos.requests.InvestmentRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.InvestmentResponse;
import com.semicolon.grincultified.dtos.responses.InvestorResponse;
import com.semicolon.grincultified.services.investorService.InvestorService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InvestmentServiceImpl implements InvestmentService {

    private final InvestmentRepo investmentRepo;
    private final InvestorService investorService;
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



    @Override
    public List<Investment> findInvestmentByEmail(String email) {
        InvestorResponse investorResponse = investorService.findByEmail(email);
        return   investmentRepo.findAllByInvestorId(email);
    }







}
