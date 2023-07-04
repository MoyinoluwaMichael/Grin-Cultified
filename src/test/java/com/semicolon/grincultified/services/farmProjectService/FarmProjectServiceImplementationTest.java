package com.semicolon.grincultified.services.farmProjectService;

import com.semicolon.grincultified.data.models.FarmProject;
import com.semicolon.grincultified.data.models.FarmProjectStatus;
import com.semicolon.grincultified.data.models.InvestmentType;
import com.semicolon.grincultified.data.repositories.FarmProjectRepository;
import com.semicolon.grincultified.dtos.requests.FarmProjectRequest;
import com.semicolon.grincultified.dtos.responses.FarmProjectResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class FarmProjectServiceImplementationTest {
    @Autowired
    private FarmProjectRepository farmProjectRepository;
    private FarmProjectRequest farmProjectRequest;
    private FarmProjectResponse farmProjectResponse;
    private FarmProjectService farmProjectService;

    @BeforeEach
    void startWith(){
        farmProjectRequest = new FarmProjectRequest();
        farmProjectRequest.setDescription("Expanding Carrot Production in Lavun LGA of Nigeria");
        farmProjectRequest.setFarmProduceSummary("Carrot");
        farmProjectRequest.setNumberOfInvestors(10L);
        farmProjectRequest.setPayoutType("Easy Cash");
        farmProjectRequest.setRoi(30);
        farmProjectRequest.setStartDate(LocalDateTime.now());
        farmProjectRequest.setMaturityDate(LocalDateTime.now());
        farmProjectRequest.setStatus(FarmProjectStatus.AVAILABLE);
        farmProjectRequest.setInvestmentType(InvestmentType.FIXED_INCOME);
        farmProjectRequest.setPictures("Our Picture");

        var project = farmProjectService.createFarmProject(farmProjectRequest);
        Assertions.assertThat(project).isNotNull();
    }
    @Test
    public void test_That_farm_Project_CanSave() {
        Assertions.assertThat(farmProjectResponse).isNotNull();

    }
}