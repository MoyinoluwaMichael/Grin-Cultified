package com.semicolon.grincultified;

import com.semicolon.grincultified.data.models.FarmProject;
import com.semicolon.grincultified.data.models.InvestmentType;
import com.semicolon.grincultified.data.repositories.AdminRepository;
import com.semicolon.grincultified.data.repositories.OtpRepository;
import com.semicolon.grincultified.data.repositories.UserRepository;
import com.semicolon.grincultified.dtos.requests.FarmProjectCreationRequest;
import com.semicolon.grincultified.services.farmProjectService.FarmProjectService;
import com.semicolon.grincultified.services.investorService.InvestorService;
import com.semicolon.grincultified.services.temporaryUserService.TemporaryUserService;
import com.semicolon.grincultified.services.userService.UserService;
import com.semicolon.grincultified.utilities.AppUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class GrinCultifiedApplicationTests {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private InvestorService investorService;
	@Autowired
	private TemporaryUserService temporaryUserService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AdminRepository adminRepository;
	private FarmProjectCreationRequest farmProjectCreationRequest;
	@Autowired
	private OtpRepository otpRepository;
	@Autowired
	private UserService userService;
	private FarmProject farmProjectResponse;
	@Autowired
	private FarmProjectService farmProjectService;

	@Test
	void contextLoads() {
		adminRepository.deleteAll();
		investorService.deleteAll();
		userRepository.deleteAll();
		temporaryUserService.deleteAll();
		otpRepository.deleteAll();
	}

	@Test
	void addUser(){
		userRepository.deleteById(302L);
//		userRepository.deleteById(202L);
	}

	@Test
	void addProjects(){
		farmProjectCreationRequest = new FarmProjectCreationRequest();
		farmProjectCreationRequest.setFarmProduceSummary("Vegetables Project");
		farmProjectCreationRequest.setDescription("Expanding Vegetables Production in Wasinmi LGA of Nigeria");
		farmProjectCreationRequest.setPayoutType("Easy Cash");
		farmProjectCreationRequest.setRoi(20);
		farmProjectCreationRequest.setStartDate(LocalDateTime.of(2023, 9, 27, 0, 0));
		farmProjectCreationRequest.setMaturityDate(LocalDateTime.of(2024, 10, 25, 0,0));
		farmProjectCreationRequest.setInvestmentType(InvestmentType.FIXED_INCOME);
		farmProjectCreationRequest.setLocation("Wasinmi");

		farmProjectResponse = farmProjectService.createFarmProject(farmProjectCreationRequest);
		assertNotNull(farmProjectResponse);
	}

	@Test void hashPassword(){
		System.out.println(passwordEncoder.encode("Renike@123"));
		System.out.println(passwordEncoder.matches("Renike@123", "$2a$10$UoQpD4QCjzxnOkAR4xmU5.O5qnLeqojFFTa9KgOAe4WHM74fI5bf."));
	}

}
