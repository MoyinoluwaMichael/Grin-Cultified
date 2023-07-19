package com.semicolon.grincultified.services.statisticsService;

import com.semicolon.grincultified.dtos.responses.AdminDashboardStatistic;
import com.semicolon.grincultified.dtos.responses.InvestorDashboardStatistic;
import com.semicolon.grincultified.dtos.responses.ProjectInvestmentsStatistics;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StatisticsService {
    public ResponseEntity<InvestorDashboardStatistic> getInvestorDashboardStatistics(String investorEmail);

    ResponseEntity<List<ProjectInvestmentsStatistics>> getAllOngoingProjectInvestmentsStatistics();

    ResponseEntity<AdminDashboardStatistic> getAdminDashboardStatistics();

}
