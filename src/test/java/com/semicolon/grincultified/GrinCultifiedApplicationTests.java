package com.semicolon.grincultified;

import com.semicolon.grincultified.data.models.*;
import com.semicolon.grincultified.data.repositories.*;
import com.semicolon.grincultified.dtos.requests.AdminRegistrationRequest;
import com.semicolon.grincultified.dtos.requests.FarmProjectCreationRequest;
import com.semicolon.grincultified.exception.AdminInvitationNotFoundException;
import com.semicolon.grincultified.exception.AuthenticationException;
import com.semicolon.grincultified.services.farmProjectService.FarmProjectService;
import com.semicolon.grincultified.services.investorService.InvestorService;
import com.semicolon.grincultified.services.superAdminService.SuperAdminService;
import com.semicolon.grincultified.services.temporaryUserService.TemporaryUserService;
import com.semicolon.grincultified.services.userService.UserService;
import com.semicolon.grincultified.utilities.AppUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class GrinCultifiedApplicationTests {
	@Autowired
	private FarmProjectRepository farmProjectRepository;
	@Autowired
	private FarmerRepo farmerRepo;
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
	@Autowired
	private SuperAdminService superAdminService;
	private FarmProject farmProjectResponse;
	@Autowired
	private FarmProjectService farmProjectService;
	@Autowired
	private InvestmentRepo investmentRepo;

	@Test
	void contextLoads() {
//		investmentRepo.deleteAll();
//		adminRepository.deleteAll();
//		investorService.deleteAll();
//		userRepository.deleteAll();
//		temporaryUserService.deleteAll();
//		otpRepository.deleteAll();
	}

	@Test
	void addUser(){
		BigDecimal amount = BigDecimal.ZERO;
		for (var each : farmProjectRepository.findAll()) {
			each.getInvestmentPlan().setAmountPerUnit(new BigDecimal(5000).add(amount));
			farmProjectRepository.save(each);
			amount = amount.add(new BigDecimal(500));
		}
	}

	@Test
	void addProjects(){
		farmProjectCreationRequest = new FarmProjectCreationRequest();
		farmProjectCreationRequest.setFarmProduceSummary("Vegetables Project");
		farmProjectCreationRequest.setDescription("Expanding Vegetables Production in Wasinmi LGA of Nigeria");
		farmProjectCreationRequest.setPayoutType("Easy Cash");
		farmProjectCreationRequest.setRoi(20);
		farmProjectCreationRequest.setStartDate(LocalDate.of(2023, 9, 27));
		farmProjectCreationRequest.setMaturityDate(LocalDate.of(2024, 10, 25));
		farmProjectCreationRequest.setInvestmentType(InvestmentType.FIXED_INCOME);
		farmProjectCreationRequest.setLocation("Wasinmi");

		farmProjectResponse = farmProjectService.createFarmProject(farmProjectCreationRequest);
		assertNotNull(farmProjectResponse);
	}

	@Test void hashPassword(){
		System.out.println(passwordEncoder.encode("Renike@123"));
		System.out.println(passwordEncoder.matches("Renike@123", "$2a$10$UoQpD4QCjzxnOkAR4xmU5.O5qnLeqojFFTa9KgOAe4WHM74fI5bf."));
	}

	@Test void bigDecimalTest(){
		BigDecimal amount = BigDecimal.valueOf(10000);
		int noOfUnits = Integer.parseInt(amount.divide(BigDecimal.valueOf(5000)).toPlainString());
		System.out.println(noOfUnits);
	}

	@Test void createSuperAdmin() throws AuthenticationException, AdminInvitationNotFoundException {
		AdminRegistrationRequest request = new AdminRegistrationRequest();
		request.setEmailAddress("nwachukwusamuel123@gmail.com");
		request.setPassword("Mr-S-Square");
		request.setFirstName("Samuel");
		request.setLastName("Kulitech");
		superAdminService.registerSuperAdminAccount(request);
	}

	@Test void deleteUser(){
//		userRepository.deleteById(654L);
//		userRepository.deleteById(1002L);
//		investorService.deleteAll();
//		adminRepository.deleteAll();
//		Admin admin = adminRepository.findAdminByUser_EmailAddress("Sgreensneh@gmail.com").get();
//		admin.getUser().setEmailAddress("Sgreen");
//		adminRepository.save(admin);
		farmProjectRepository.deleteAll();
		investmentRepo.deleteAll();
		farmerRepo.deleteAll();
	}

}
