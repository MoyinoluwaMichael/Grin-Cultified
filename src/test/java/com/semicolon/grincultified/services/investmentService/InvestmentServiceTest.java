package com.semicolon.grincultified.services.investmentService;

import com.semicolon.grincultified.data.models.InvestmentReturnType;
import com.semicolon.grincultified.dtos.requests.InvestmentRegistrationRequest;
import com.semicolon.grincultified.dtos.requests.InvestorRegistrationRequest;
import com.semicolon.grincultified.dtos.requests.OtpVerificationRequest;
import com.semicolon.grincultified.dtos.responses.InvestmentResponse;
import com.semicolon.grincultified.dtos.responses.InvestorResponse;
import com.semicolon.grincultified.exception.DuplicateInvestorException;
import com.semicolon.grincultified.exception.InvalidOtpException;
import com.semicolon.grincultified.exception.TemporaryInvestorDoesNotExistException;
import com.semicolon.grincultified.services.investmentservice.InvestmentService;
import com.semicolon.grincultified.services.investorService.InvestorService;
import com.semicolon.grincultified.services.temporaryUserService.TemporaryUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class InvestmentServiceTest {
    @Autowired
    private InvestorService investorService;
    private InvestorRegistrationRequest investorRegistrationRequest;
    private OtpVerificationRequest otpVerificationRequest;
    private String otp;
    @Autowired
    private InvestmentService investmentService;
    private InvestmentRegistrationRequest investmentRegistrationRequest;
    private ResponseEntity<InvestmentResponse>  investmentResponse;
    private ResponseEntity<InvestorResponse>  investorResponse;
    @Autowired
    private TemporaryUserService temporaryUserService;


    @BeforeEach
    void setUp() throws DuplicateInvestorException, TemporaryInvestorDoesNotExistException, InvalidOtpException {
//        investorService.deleteAll();
//        investmentService.deleteAll();
//        temporaryUserService.deleteAll();
//        otpVerificationRequest = new OtpVerificationRequest();
//        investorRegistrationRequest = new InvestorRegistrationRequest();
//        investorRegistrationRequest.setEmailAddress("jenob77428@devswp.com");
//        investorRegistrationRequest.setFirstName("first");
//        investorRegistrationRequest.setLastName("last");
//        investorRegistrationRequest.setPhoneNumber("0909999999");
//        investorRegistrationRequest.setPassword("1234");
//        var response = investorService.initiateRegistration(investorRegistrationRequest);
//        otp = response.getBody().getData();
//        otpVerificationRequest.setOtp(otp);
//        otpVerificationRequest.setEmailAddress("jenob77428@devswp.com");
//        investorResponse = investorService.confirmRegistration(otpVerificationRequest);
        InvestorResponse investorResponse1 = investorService.findByEmail("m.ogunmoroti@native.semicolon.africa");
        investmentRegistrationRequest = new InvestmentRegistrationRequest();
        investmentRegistrationRequest.setAmount(BigDecimal.valueOf(5000));
        investmentRegistrationRequest.setFarmProjectId(1L);
        investmentRegistrationRequest.setInvestorId(investorResponse1.getId());
        investmentRegistrationRequest.setReturnType(InvestmentReturnType.MONEY);
        investmentRegistrationRequest.setStartingDate(LocalDateTime.now());
        investmentRegistrationRequest.setRedemptionDate(LocalDateTime.now().plusMonths(9L));
        investmentResponse = investmentService.initiateInvestment(investmentRegistrationRequest);
    }

    @Test
    public void investorCanInvestTest() {
        assertThat(investmentResponse).isNotNull();
    }

    @Test
    public void findInvestmentByEmailTest() {
        var foundInvestment = investmentService.findAllOngoingInvestmentsByEmail(investorRegistrationRequest.getEmailAddress());
        assertTrue(foundInvestment.getBody().size() > 0);
    }
}
