package com.semicolon.grincultified.services.investmentservice;

import com.semicolon.grincultified.data.models.Investment;
import com.semicolon.grincultified.data.models.InvestmentStatus;
import com.semicolon.grincultified.data.repositories.InvestmentRepo;
import com.semicolon.grincultified.dtos.requests.InvestmentRegistrationRequest;
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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.semicolon.grincultified.data.models.InvestmentStatus.MATURE;
import static com.semicolon.grincultified.data.models.InvestmentStatus.ONGOING;

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
        List<InvestmentResponse> investmentResponses = new ArrayList<>(investments.stream().map(i -> modelMapper.map(i, InvestmentResponse.class)).toList());
        investmentResponses.sort(Comparator.comparing(InvestmentResponse::getCreatedAt));
        return ResponseEntity.ok().body(investmentResponses);
    }

    @Override
    public void deleteAll() {
        investmentRepo.deleteAll();
    }

    @Override
    public ResponseEntity<List<InvestmentResponse>> getAllOngoingProjectInvestments() {

        return null;
    }

    @Override
    public List<Investment> findAllByInvestorIdAndStatusOrStatus(Long id, InvestmentStatus status, InvestmentStatus status2) {
        return investmentRepo.findAllByInvestorIdAndStatusOrStatus(id, status, status2);
    }

    @Override
    public Long countAllByFarmProjectId(Long farmProjectId) {
        return investmentRepo.countAllByFarmProjectId(farmProjectId);
    }

    @Override
    public BigDecimal calculateAllInvestmentsAmountByFarmProjectId(Long farmProjectId) {
        BigDecimal amount = BigDecimal.ZERO;
        for (var each : investmentRepo.findAllByFarmProjectId(farmProjectId)) {
            amount = amount.add(each.getAmount());
        }
        return amount;
    }

}
