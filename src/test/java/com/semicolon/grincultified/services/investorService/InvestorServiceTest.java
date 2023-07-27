package com.semicolon.grincultified.services.investorService;

import com.semicolon.grincultified.dtos.requests.CompleteRegistrationRequest;
import com.semicolon.grincultified.dtos.requests.InvestorRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.GenericResponse;
import com.semicolon.grincultified.dtos.responses.InvestorResponse;
import com.semicolon.grincultified.exception.*;
import com.semicolon.grincultified.services.temporaryUserService.TemporaryUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class InvestorServiceTest {
    @Autowired
    private InvestorService investorService;
    private InvestorRegistrationRequest investorRegistrationRequest;
    private CompleteRegistrationRequest completeRegistrationRequest;
    private String otp;
    private ResponseEntity<GenericResponse<String>> investorRegistrationResponse;
    private ResponseEntity<InvestorResponse>  investorResponse;
    @Autowired
    private TemporaryUserService temporaryUserService;

    @BeforeEach
    public void setUp() throws DuplicateInvestorException, TemporaryInvestorDoesNotExistException, InvalidOtpException, AdminAlreadyExistException, InvalidEmailException {
        investorService.deleteAll();
        temporaryUserService.deleteAll();
        completeRegistrationRequest = new CompleteRegistrationRequest();
        investorRegistrationRequest = new InvestorRegistrationRequest();
        investorRegistrationRequest.setEmailAddress("jenob77428@devswp.com");
        investorRegistrationRequest.setFirstName("first");
        investorRegistrationRequest.setLastName("last");
        investorRegistrationRequest.setPhoneNumber("0909999999");
        investorRegistrationRequest.setPassword("1234");
        investorRegistrationResponse = investorService.initiateRegistration(investorRegistrationRequest);
        otp = investorRegistrationResponse.getBody().getData();
        completeRegistrationRequest.setEmailAddress("jenob77428@devswp.com");
    }

    @Test
    public void initiateRegistrationTest() {
        assertNotNull(investorRegistrationResponse.getBody());
        assertNotNull(investorRegistrationResponse.getBody().getMessage());
    }

    @Test
    public void confirmRegistrationTest() {
        assertNotNull(investorResponse);
    }
}
