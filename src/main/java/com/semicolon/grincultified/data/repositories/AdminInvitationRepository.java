package com.semicolon.grincultified.data.repositories;

import com.semicolon.grincultified.data.models.AdminInvitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminInvitationRepository extends JpaRepository<AdminInvitation, String> {

    Optional<AdminInvitation> findByEmail(String email);
}
