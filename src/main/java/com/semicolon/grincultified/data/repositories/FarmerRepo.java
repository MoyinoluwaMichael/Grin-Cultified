package com.semicolon.grincultified.data.repositories;

import com.semicolon.grincultified.data.models.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmerRepo extends JpaRepository<Farmer, Long> {

}
