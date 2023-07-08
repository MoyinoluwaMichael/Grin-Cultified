package com.semicolon.grincultified.data.repositories;

import com.semicolon.grincultified.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAddress(String email);
}
