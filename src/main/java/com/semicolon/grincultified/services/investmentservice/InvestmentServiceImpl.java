package com.semicolon.grincultified.services.investmentservice;

import com.semicolon.grincultified.data.models.Investment;
import com.semicolon.grincultified.data.models.InvestmentStatus;
import com.semicolon.grincultified.data.repositories.InvestmentRepo;
import com.semicolon.grincultified.dtos.requests.InvestmentRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.DashboardStatistic;
import com.semicolon.grincultified.dtos.responses.InvestmentResponse;
import com.semicolon.grincultified.dtos.responses.InvestorResponse;
import com.semicolon.grincultified.services.farmProjectService.FarmProjectService;
import com.semicolon.grincultified.services.investorService.InvestorService;
import com.semicolon.grincultified.utilities.CultifyMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;

import static com.semicolon.grincultified.data.models.InvestmentStatus.MATURE;
import static com.semicolon.grincultified.data.models.InvestmentStatus.ONGOING;
import static com.semicolon.grincultified.utilities.AppUtils.NO_INVESTMENTS_YET;

@Service
@AllArgsConstructor
public class InvestmentServiceImpl implements InvestmentService {

    private final InvestmentRepo investmentRepo;
    private final FarmProjectService farmProjectService;
    private final InvestorService investorService;
    private final ModelMapper modelMapper;
    private final CultifyMapper cultifyMapper;


    @Override
    public ResponseEntity<InvestmentResponse> initiateInvestment(InvestmentRegistrationRequest investmentRegistrationRequest) {
        Investment investment = cultifyMapper.map(investmentRegistrationRequest);
        Investment savedInvestment = investmentRepo.save(investment);
        InvestmentResponse investmentNow = modelMapper.map(savedInvestment, InvestmentResponse.class);
        farmProjectService.updateProjectAvailability(investmentRegistrationRequest.getFarmProjectId(), investmentRegistrationRequest.getAmount());
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
    public ResponseEntity<List<InvestmentResponse>> findAllOngoingInvestmentsByEmail(String email) {
        InvestorResponse investorResponse = investorService.findByEmail(email);
        List<Investment> investments = investmentRepo.findAllByInvestorIdAndStatusOrStatus(investorResponse.getId(), ONGOING, MATURE);
        List<InvestmentResponse> investmentResponses = investments.stream().map(i->modelMapper.map(i, InvestmentResponse.class)).toList();
        return ResponseEntity.ok().body(investmentResponses);
    }

    @Override
    public void deleteAll() {
        investmentRepo.deleteAll();
    }


    @Override
    public ResponseEntity<DashboardStatistic> getDashboardStatistics(String investorEmail) {
        InvestorResponse investorResponse = investorService.findByEmail(investorEmail);
        List<Investment> investments = investmentRepo.findAllByInvestorIdAndStatusOrStatus(investorResponse.getId(), ONGOING, MATURE);
        int totalNumberOfInvestments = investments.size();
        BigDecimal totalAmountInvested = getTotalAmountInvested(investments);
        String upcomingPaymentDate = getUpcomingPaymentDate(investments);
        DashboardStatistic statistic = new DashboardStatistic(totalNumberOfInvestments, totalAmountInvested, upcomingPaymentDate);
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

}
