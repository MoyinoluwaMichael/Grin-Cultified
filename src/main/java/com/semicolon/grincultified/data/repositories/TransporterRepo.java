package com.semicolon.grincultified.data.repositories;

import com.semicolon.grincultified.data.models.Transporter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransporterRepo extends JpaRepository<Transporter, Long> {
}
