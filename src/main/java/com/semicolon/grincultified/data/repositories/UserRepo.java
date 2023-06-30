package com.semicolon.grincultified.data.repositories;

import com.semicolon.grincultified.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
