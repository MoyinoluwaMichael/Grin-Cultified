package com.semicolon.grincultified.data.repositories;

import com.semicolon.grincultified.data.models.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvestmentRepo extends JpaRepository<Investment, Long>{
    List<Investment> findAllByInvestorId(Long id);


}
