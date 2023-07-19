package com.semicolon.grincultified.data.repositories;

import com.semicolon.grincultified.data.models.Investment;
import com.semicolon.grincultified.data.models.InvestmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface InvestmentRepo extends JpaRepository<Investment, Long>{
    List<Investment> findAllByInvestorIdAndStatusOrStatus(Long investorId, InvestmentStatus status, InvestmentStatus status2);
    List<Investment> findAllByFarmProjectId(Long farmProjectId);
    Long countAllByFarmProjectId(Long farmProjectId);

}

