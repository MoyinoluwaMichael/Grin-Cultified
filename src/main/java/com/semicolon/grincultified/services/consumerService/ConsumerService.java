package com.semicolon.grincultified.services.consumerService;

import com.semicolon.grincultified.dtos.requests.ConsumerRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.ConsumerRegistrationResponse;

public interface ConsumerService {
    ConsumerRegistrationResponse register(ConsumerRegistrationRequest consumerRegistrationRequest);

//    UserResponse findUserById(Long id);
}
