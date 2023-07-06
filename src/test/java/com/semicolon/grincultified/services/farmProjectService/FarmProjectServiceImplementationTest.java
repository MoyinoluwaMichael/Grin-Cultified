package com.semicolon.grincultified.services.farmProjectService;

import com.semicolon.grincultified.data.models.FarmProject;
import com.semicolon.grincultified.data.models.FarmProjectStatus;
import com.semicolon.grincultified.data.models.InvestmentType;
import com.semicolon.grincultified.dtos.requests.FarmProjectCreationRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class FarmProjectServiceImplementationTest {

    private FarmProjectCreationRequest farmProjectCreationRequest;
    private FarmProject farmProjectResponse;
    @Autowired
    private FarmProjectService farmProjectService;

    @BeforeEach
    void setUp(){
        farmProjectService.deleteAll();
        farmProjectCreationRequest = new FarmProjectCreationRequest();
        farmProjectCreationRequest.setDescription("Expanding Carrot Production in Lavun LGA of Nigeria");
        farmProjectCreationRequest.setFarmProduceSummary("Carrot");
        farmProjectCreationRequest.setNumberOfInvestors(10L);
        farmProjectCreationRequest.setPayoutType("Easy Cash");
        farmProjectCreationRequest.setRoi(30);
        farmProjectCreationRequest.setStartDate(LocalDateTime.now());
        farmProjectCreationRequest.setMaturityDate(LocalDateTime.now());
        farmProjectCreationRequest.setStatus(FarmProjectStatus.AVAILABLE);
        farmProjectCreationRequest.setInvestmentType(InvestmentType.FIXED_INCOME);
        farmProjectCreationRequest.setPictures("Our Picture");

        farmProjectResponse = farmProjectService.createFarmProject(farmProjectCreationRequest);

    }
    @Test
    public void test_That_farm_Project_CanSave() {
        Assertions.assertThat(farmProjectResponse).isNotNull();

    }
}