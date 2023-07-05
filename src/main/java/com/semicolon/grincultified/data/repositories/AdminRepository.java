package com.semicolon.grincultified.data.repositories;

import com.semicolon.grincultified.data.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findAdminByUser_EmailAddress(String email);
}
