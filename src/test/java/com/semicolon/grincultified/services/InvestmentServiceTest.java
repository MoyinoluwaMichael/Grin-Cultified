package com.semicolon.grincultified.services;

import com.semicolon.grincultified.data.models.InvestmentReturnType;
import com.semicolon.grincultified.data.models.RedemptionStatus;
import com.semicolon.grincultified.dtos.requests.InvestmentRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.InvestmentResponse;
import com.semicolon.grincultified.services.investmentservice.InvestmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

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
        investmentRegistrationRequest.setFarmProjectId(1L);
        investmentRegistrationRequest.setInvestorId(2L);
        investmentRegistrationRequest.setRedemptionStatus(RedemptionStatus.PENDING);
        investmentRegistrationRequest.setReturnType(InvestmentReturnType.MONEY);
        investmentRegistrationRequest.getStatus();

        investmentResponse = investmentService.initiateInvestment(investmentRegistrationRequest);

    }

    @Test
    public void investorCanInvestTest(){
    assertThat(investmentResponse).isNotNull();

    }
}
