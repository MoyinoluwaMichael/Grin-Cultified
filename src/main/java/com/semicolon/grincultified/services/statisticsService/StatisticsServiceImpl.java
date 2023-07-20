package com.semicolon.grincultified.services.statisticsService;

import com.semicolon.grincultified.data.models.FarmProject;
import com.semicolon.grincultified.data.models.Investment;
import com.semicolon.grincultified.dtos.responses.AdminDashboardStatistic;
import com.semicolon.grincultified.dtos.responses.InvestorDashboardStatistic;
import com.semicolon.grincultified.dtos.responses.InvestorResponse;
import com.semicolon.grincultified.dtos.responses.ProjectInvestmentsStatistics;
import com.semicolon.grincultified.services.adminService.AdminService;
import com.semicolon.grincultified.services.farmProjectService.FarmProjectService;
import com.semicolon.grincultified.services.farmerService.FarmerService;
import com.semicolon.grincultified.services.investmentservice.InvestmentService;
import com.semicolon.grincultified.services.investorService.InvestorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.semicolon.grincultified.data.models.InvestmentStatus.MATURE;
import static com.semicolon.grincultified.data.models.InvestmentStatus.ONGOING;
import static com.semicolon.grincultified.utilities.AppUtils.NO_INVESTMENTS_YET;

@Service
@AllArgsConstructor
public class StatisticsServiceImpl implements StatisticsService{
    private final InvestorService investorService;
    private final InvestmentService investmentService;
    private final FarmProjectService farmProjectService;
    private final FarmerService farmerService;
    private final AdminService adminService;

    @Override
    public ResponseEntity<InvestorDashboardStatistic> getInvestorDashboardStatistics(String investorEmail) {
        InvestorResponse investorResponse = investorService.findByEmail(investorEmail);
        List<Investment> investments = investmentService.findAllByInvestorIdAndStatusOrStatus(investorResponse.getId(), ONGOING, MATURE);
        investments.sort(Comparator.comparing(Investment::getRedemptionDate));
        int totalNumberOfInvestments = investments.size();
        BigDecimal totalAmountInvested = getTotalAmountInvested(investments);
        String upcomingPaymentDate = getUpcomingPaymentDate(investments);
        InvestorDashboardStatistic statistic = new InvestorDashboardStatistic(totalNumberOfInvestments, totalAmountInvested, upcomingPaymentDate);
        return ResponseEntity.ok().body(statistic);
    }
    private String getUpcomingPaymentDate(List<Investment> investments) {
        if (investments.size()!= 0){
            return investments.get(0).getRedemptionDate().toString().split("T")[0];
        }
        return NO_INVESTMENTS_YET;
    }

    private BigDecimal getTotalAmountInvested(List<Investment> investments) {
        BigDecimal amount = new BigDecimal(BigInteger.ZERO);
        for (var investment : investments) {
            amount = amount.add(investment.getAmount());
        }
        return amount;
    }

    @Override
    public ResponseEntity<List<ProjectInvestmentsStatistics>> getAllOngoingProjectInvestmentsStatistics() {
        List<FarmProject> notMaturedFarmProjects = farmProjectService.findAllNotMaturedProjects();
        List<ProjectInvestmentsStatistics> projectInvestmentsStatisticsData = new ArrayList<>();
        for (var each : notMaturedFarmProjects) {
            ProjectInvestmentsStatistics projectInvestmentsStatistics = calculateProjectInvestmentsStatistics(each);
            projectInvestmentsStatisticsData.add(projectInvestmentsStatistics);
        }
        projectInvestmentsStatisticsData.sort(Comparator.comparing(ProjectInvestmentsStatistics::getMaturityDate));
        return ResponseEntity.ok().body(projectInvestmentsStatisticsData);
    }
    private ProjectInvestmentsStatistics calculateProjectInvestmentsStatistics(FarmProject each) {
        ProjectInvestmentsStatistics projectInvestmentsStatistics = new ProjectInvestmentsStatistics();
        projectInvestmentsStatistics.setFarmProjectName(each.getFarmProduceSummary());
        Long numberOfInvestors = investmentService.countAllByFarmProjectId(each.getId());
        projectInvestmentsStatistics.setNumberOfInvestors(numberOfInvestors);
        BigDecimal amount = investmentService.calculateAllInvestmentsAmountByFarmProjectId(each.getId());
        projectInvestmentsStatistics.setTotalAmountInvested(amount);
        int roi = each.getInvestmentPlan().getRoi();
        projectInvestmentsStatistics.setRoi(roi);
        BigDecimal interest = amount.multiply(BigDecimal.valueOf(roi/100.0));
        BigDecimal expectedAmount = amount.add(interest).round(MathContext.DECIMAL32);
        projectInvestmentsStatistics.setTotalExpectedAmount(expectedAmount);
        projectInvestmentsStatistics.setMaturityDate(each.getInvestmentPlan().getMaturityDate());
        return projectInvestmentsStatistics;
    }

    @Override
    public ResponseEntity<AdminDashboardStatistic> getAdminDashboardStatistics() {
        List<FarmProject> farmProjects = farmProjectService.findAllNotMaturedProjects();
        farmProjects.sort(Comparator.comparing(farmProject -> farmProject.getInvestmentPlan().getMaturityDate()));
        AdminDashboardStatistic dashboardStatistic = new AdminDashboardStatistic();
        dashboardStatistic.setTotalNumberOfOngoingFarmProject(farmProjects.size());
        BigDecimal totalAmountOfMoneyInvestedByTheInvestors = BigDecimal.ZERO;
        for (var each : farmProjects) {
            BigDecimal totalAmountInvestedInProject = investmentService.calculateAllInvestmentsAmountByFarmProjectId(each.getId());
            totalAmountOfMoneyInvestedByTheInvestors = totalAmountOfMoneyInvestedByTheInvestors.add(totalAmountInvestedInProject);
        }
        dashboardStatistic.setTotalAmountOfMoneyInvestedByTheInvestors(totalAmountOfMoneyInvestedByTheInvestors.round(MathContext.DECIMAL32));
        LocalDate nextRedeemedProject = farmProjects.size() == 0? null: farmProjects.get(0).getInvestmentPlan().getMaturityDate();
        dashboardStatistic.setNextRedeemedProject(nextRedeemedProject);
        dashboardStatistic.setTotalNumberOfFarmers(farmerService.count());
        dashboardStatistic.setTotalNumberOfInvestors(investorService.count());
        dashboardStatistic.setTotalNumberOfSystemAdmin(adminService.count());
        return ResponseEntity.ok().body(dashboardStatistic);
    }


}
