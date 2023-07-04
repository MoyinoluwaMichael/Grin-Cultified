package com.semicolon.grincultified.data.repositories;

import com.semicolon.grincultified.dtos.requests.InvestorRegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemporaryUserRepo extends JpaRepository<InvestorRegistrationRequest, String> {
    Optional<InvestorRegistrationRequest> findByEmailAddress(String emailAddress);
}
