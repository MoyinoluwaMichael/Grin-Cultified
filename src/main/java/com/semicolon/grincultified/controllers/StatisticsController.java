package com.semicolon.grincultified.controllers;

import com.semicolon.grincultified.dtos.responses.AdminDashboardStatistic;
import com.semicolon.grincultified.dtos.responses.InvestorDashboardStatistic;
import com.semicolon.grincultified.dtos.responses.ProjectInvestmentsStatistics;
import com.semicolon.grincultified.services.statisticsService.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/semicolon/cultify/v1/api/")
public class StatisticsController {
    private final StatisticsService statisticsService;


    @GetMapping("/getAllOngoingProjectInvestmentsStatistics")
    public ResponseEntity<List<ProjectInvestmentsStatistics>> getAllOngoingProjectInvestmentsStatistics() {
        return statisticsService.getAllOngoingProjectInvestmentsStatistics();
    }

    @GetMapping("/getInvestorDashboardStatistics/{investorEmail}")
    public ResponseEntity<InvestorDashboardStatistic> getInvestorDashboardStatistics(@PathVariable String investorEmail){
        return statisticsService.getInvestorDashboardStatistics(investorEmail);
    }

    @GetMapping("/getAdminDashboardStatistics")
    public ResponseEntity<AdminDashboardStatistic> getAdminDashboardStatistics(){
        return statisticsService.getAdminDashboardStatistics();
    }
}
