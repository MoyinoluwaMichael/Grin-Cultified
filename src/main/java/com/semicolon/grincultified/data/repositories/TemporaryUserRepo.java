package com.semicolon.grincultified.data.repositories;

import com.semicolon.grincultified.data.models.User;
import org.springframework.data.repository.CrudRepository;

public interface TemporaryUserRepo extends CrudRepository<User, Long> {
}
