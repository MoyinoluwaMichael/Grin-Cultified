package com.semicolon.grincultified.data.repositories;

import com.semicolon.grincultified.data.models.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp, Long> {
}
