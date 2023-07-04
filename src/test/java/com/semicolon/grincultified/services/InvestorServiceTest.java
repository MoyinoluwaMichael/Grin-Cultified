package com.semicolon.grincultified.services;

import com.semicolon.grincultified.dtos.requests.InvestorRegistrationRequest;
import com.semicolon.grincultified.dtos.requests.OtpVerificationRequest;
import com.semicolon.grincultified.dtos.responses.InvestorRegistrationResponse;
import com.semicolon.grincultified.exception.DuplicateInvestorException;
import com.semicolon.grincultified.exception.TemporaryInvestorDoesNotExistException;
import com.semicolon.grincultified.services.investorService.InvestorService;
import com.semicolon.grincultified.services.otpService.OtpService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
class InvestorServiceTest {
    @Autowired
    private InvestorService investorService;
    private InvestorRegistrationRequest investorRegistrationRequest;
    private OtpVerificationRequest otpVerificationRequest;
    @Autowired
    private OtpService otpService;
    private String otp;
    private ResponseEntity<InvestorRegistrationResponse> investorRegistrationResponse;

    @BeforeEach
    public void setUp() throws DuplicateInvestorException {
        otpVerificationRequest = new OtpVerificationRequest();
        investorRegistrationRequest = new InvestorRegistrationRequest();
        investorRegistrationRequest.setEmailAddress("jenob77428@devswp.com");
        investorRegistrationRequest.setFirstName("first");
        investorRegistrationRequest.setLastName("last");
        investorRegistrationRequest.setPhoneNumber("0909999999");
        investorRegistrationRequest.setPassword("1234");
        investorRegistrationResponse = investorService.initiateRegistration(investorRegistrationRequest);
    }

    @Test
    public void initiateRegistrationTest() {
        otp = investorRegistrationResponse.getBody().getOtp();
        assertNotNull(investorRegistrationResponse.getBody());
        assertNotNull(investorRegistrationResponse.getBody().getMessage());
    }

    @Test
    public void confirmRegistrationTest() throws TemporaryInvestorDoesNotExistException {
        otpVerificationRequest.setOtp(otp);
        otpVerificationRequest.setEmailAddress("jenob77428@devswp.com");
        InvestorRegistrationResponse investorRegistrationResponse = investorService.confirmRegistration(otpVerificationRequest);
        assertNotNull(investorRegistrationResponse);
    }


}
