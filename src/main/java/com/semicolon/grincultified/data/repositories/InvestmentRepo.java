package com.semicolon.grincultified.data.repositories;

import com.semicolon.grincultified.data.models.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentRepo extends JpaRepository<Investment, Long>{
}
