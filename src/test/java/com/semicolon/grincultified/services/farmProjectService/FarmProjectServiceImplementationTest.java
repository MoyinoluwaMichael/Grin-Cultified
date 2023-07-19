package com.semicolon.grincultified.services.farmProjectService;

import com.semicolon.grincultified.data.models.FarmProject;
import com.semicolon.grincultified.data.models.FarmProjectStatus;
import com.semicolon.grincultified.data.models.InvestmentType;
import com.semicolon.grincultified.data.repositories.InvestmentRepo;
import com.semicolon.grincultified.dtos.requests.FarmProjectCreationRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;


import java.time.LocalDate;

import java.time.LocalDateTime;

@SpringBootTest
class FarmProjectServiceImplementationTest {

    private FarmProjectCreationRequest farmProjectCreationRequest;
    private FarmProject farmProjectResponse;
    @Autowired
    private FarmProjectService farmProjectService;
    @Autowired
    private InvestmentRepo investmentRepo;

    @BeforeEach
    void setUp(){
        investmentRepo.deleteAll();
        farmProjectService.deleteAll();
        farmProjectCreationRequest = new FarmProjectCreationRequest();
        farmProjectCreationRequest.setDescription("Expanding Carrot Production in Lavun LGA of Nigeria");
        farmProjectCreationRequest.setFarmProduceSummary("Carrot");
        farmProjectCreationRequest.setPayoutType("Easy Cash");
        farmProjectCreationRequest.setRoi(30);

        farmProjectCreationRequest.setLocation("52, wukari srteet Kano state");
        farmProjectCreationRequest.setPricePerUnit(BigDecimal.valueOf(200));
        farmProjectCreationRequest.setStartDate(LocalDate.from(LocalDateTime.now()));
        farmProjectCreationRequest.setMaturityDate(LocalDate.from(LocalDateTime.now()));

        farmProjectCreationRequest.setStartDate(LocalDate.now());
        farmProjectCreationRequest.setAmountPerUnit(BigDecimal.valueOf(5000));
        farmProjectCreationRequest.setMaturityDate(LocalDate.now());

        farmProjectCreationRequest.setInvestmentType(InvestmentType.FIXED_INCOME);

//        farmProjectResponse = farmProjectService.createFarmProject(farmProjectCreationRequest);

    }
    @Test
    public void test_That_farm_Project_CanSave() {
//        Assertions.assertThat(farmProjectResponse).isNotNull();
        for (int i = 0; i < 10; i++) {
            farmProjectResponse = farmProjectService.createFarmProject(farmProjectCreationRequest);
        }

    }
}