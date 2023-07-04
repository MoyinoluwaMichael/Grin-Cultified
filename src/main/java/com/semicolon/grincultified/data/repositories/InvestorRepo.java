package com.semicolon.grincultified.data.repositories;

import com.semicolon.grincultified.data.models.Investor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvestorRepo extends JpaRepository<Investor, Long> {
    Optional<Investor> findByUser_EmailAddressContainingIgnoreCase(String email);
}
