package com.semicolon.grincultified.services;

import com.semicolon.grincultified.dtos.requests.InvestmentRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.InvestmentResponse;
import com.semicolon.grincultified.services.investmentservice.InvestmentService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class InvestmentServiceTest {
    @Autowired
    private InvestmentService investmentService;
    private InvestmentRegistrationRequest investmentRegistrationRequest;
    private InvestmentResponse investmentResponse;


    @BeforeEach
    void setUp() {
        investmentRegistrationRequest = new InvestmentRegistrationRequest();
        investmentRegistrationRequest.setAmount(BigDecimal.valueOf(5000));
//        investmentRegistrationRequest.setFarmProjectId();


    }
}
