package com.semicolon.grincultified.data.repositories;

import com.semicolon.grincultified.dtos.requests.ConsumerRegistrationRequest;
import org.springframework.data.repository.CrudRepository;

public interface TemporaryConsumerRepo extends CrudRepository<ConsumerRegistrationRequest, String> {

}
