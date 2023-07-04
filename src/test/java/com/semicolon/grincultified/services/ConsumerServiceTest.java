package com.semicolon.grincultified.services;

import com.semicolon.grincultified.dtos.requests.ConsumerRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.ConsumerRegistrationResponse;
import com.semicolon.grincultified.services.consumerService.ConsumerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ConsumerServiceTest {
    @Autowired
    private ConsumerService consumerService;
    private ConsumerRegistrationRequest consumerRegistrationRequest;
    private ConsumerRegistrationResponse consumerRegistrationResponse;

    @BeforeEach
    void setUp() {

    }

    @Test
    public void registerUserTest(){

    }


}
