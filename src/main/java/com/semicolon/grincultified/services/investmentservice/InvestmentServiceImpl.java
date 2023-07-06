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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InvestmentServiceImpl implements InvestmentService {

    private final InvestmentRepo investmentRepo;
    private final InvestorService investorService;
    private final ModelMapper modelMapper;


    @Override
    public ResponseEntity<InvestmentResponse> initiateInvestment(InvestmentRegistrationRequest investmentRegistrationRequest) {
        Investment investment = modelMapper.map(investmentRegistrationRequest, Investment.class);
        Investment savedInvestment = investmentRepo.save(investment);
        InvestmentResponse investmentNow = modelMapper.map(savedInvestment, InvestmentResponse.class);
        return ResponseEntity.ok().body(investmentNow);
    }

    @Override
    public ResponseEntity<List<InvestmentResponse>> findAll() {
        List<InvestmentResponse> investmentResponses = investmentRepo.findAll()
                                                        .stream()
                                                        .map(i -> modelMapper.map(i, InvestmentResponse.class)).toList();
        return ResponseEntity.ok().body(investmentResponses);
    }

    @Override
    public ResponseEntity<List<InvestmentResponse>> findInvestmentByEmail(String email) {
        InvestorResponse investor = investorService.findByEmail(email);
        List<Investment> investments = investmentRepo.findAllByInvestorId(investor.getId());
        List<InvestmentResponse> investmentResponses = investments.stream().map(i->modelMapper.map(i, InvestmentResponse.class)).toList();
        return ResponseEntity.ok().body(investmentResponses);
    }







}
