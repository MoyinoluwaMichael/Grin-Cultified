package com.semicolon.grincultified;

import com.semicolon.grincultified.data.repositories.AdminRepository;
import com.semicolon.grincultified.data.repositories.OtpRepository;
import com.semicolon.grincultified.data.repositories.UserRepository;
import com.semicolon.grincultified.services.investorService.InvestorService;
import com.semicolon.grincultified.services.temporaryUserService.TemporaryUserService;
import com.semicolon.grincultified.services.userService.UserService;
import com.semicolon.grincultified.utilities.AppUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GrinCultifiedApplicationTests {
	@Autowired
	private InvestorService investorService;
	@Autowired
	private TemporaryUserService temporaryUserService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private OtpRepository otpRepository;
	@Autowired
	private UserService userService;

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

	}

}
