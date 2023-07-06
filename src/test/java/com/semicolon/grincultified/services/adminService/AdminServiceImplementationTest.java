package com.semicolon.grincultified.services.adminService;

import com.semicolon.grincultified.data.models.AdminType;
import com.semicolon.grincultified.dtos.requests.AdminRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.AdminResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AdminServiceImplementationTest {
    @Autowired
    AdminService adminService;
    AdminRegistrationRequest adminRegistrationRequest;
    AdminResponse adminResponse;

    @BeforeEach
    void setUp()  {
        adminRegistrationRequest = new AdminRegistrationRequest();
        adminRegistrationRequest.setEmailAddress("samuel123@gmail.com");
        adminRegistrationRequest.setPassword("12345");
        adminRegistrationRequest.setFirstName("Samuel");
        adminRegistrationRequest.setLastName("Sung");
        adminRegistrationRequest.setPhoneNumber("08012345678");
        //adminRegistrationRequest.setAdminType(AdminType.ORDINARY);

        adminResponse = adminService.adminRegistration(adminRegistrationRequest);
    }
    @Test
    void TestThatAdminCanRegister(){
        assertThat(adminResponse).isNotNull();
    }

}