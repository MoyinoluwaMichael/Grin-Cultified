package com.semicolon.grincultified.services.adminService;

import com.semicolon.grincultified.data.models.AdminType;
import com.semicolon.grincultified.dtos.requests.AdminRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.AdminResponse;
import com.semicolon.grincultified.exception.AdminAlreadyExistException;
import com.semicolon.grincultified.exception.AdminInvitationNotFoundException;
import com.semicolon.grincultified.exception.AdminNotFoundException;
import com.semicolon.grincultified.exception.AuthenticationException;
import com.semicolon.grincultified.services.adminInvitationService.AdminInvitationService;
import com.semicolon.grincultified.services.superAdminService.SuperAdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AdminServiceImplementationTest {
    @Autowired
    private AdminInvitationService adminInvitationService;
    @Autowired
    SuperAdminService superAdminService;
    @Autowired
    AdminService adminService;
    AdminRegistrationRequest adminRegistrationRequest;
    AdminResponse adminResponse;

    @BeforeEach
    void setUp() throws AdminInvitationNotFoundException, AdminAlreadyExistException, AdminNotFoundException, AuthenticationException {
        superAdminService.sendInvitationLink("ogunsmoyin.m@gmail.com");
        adminRegistrationRequest = new AdminRegistrationRequest();
        adminRegistrationRequest.setEmailAddress("ogunsmoyin.m@gmail.com");
        adminRegistrationRequest.setPassword("Renike@123");
        adminRegistrationRequest.setFirstName("Moyin");
        adminRegistrationRequest.setLastName("Mike");
        adminRegistrationRequest.setPhoneNumber("08146400010");

        adminResponse = adminService.register(adminRegistrationRequest).getBody();
    }
    @Test
    void TestThatAdminCanRegister(){
        assertThat(adminResponse).isNotNull();
    }

}