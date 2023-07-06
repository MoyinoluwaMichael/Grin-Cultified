package com.semicolon.grincultified.services.farmerService;

import com.semicolon.grincultified.dtos.requests.FarmerRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.FarmerResponse;
import com.semicolon.grincultified.dtos.responses.GenericResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class FarmerServiceImplTest {
    @Autowired
    private FarmerService farmerService;

    @Test void farmerRegistrationTest(){
        farmerService.deleteAll();
        FarmerRegistrationRequest farmerRegistrationRequest = new FarmerRegistrationRequest();
        farmerRegistrationRequest.setFirstName("moyin");
        farmerRegistrationRequest.setLastName("oluwa");
        farmerRegistrationRequest.setEmailAddress("moyinoluwa@example.com");
        farmerRegistrationRequest.setPassword("password");
        farmerRegistrationRequest.setCity("Lagos");
        farmerRegistrationRequest.setLandPics("land_pic1.jpg");
        farmerRegistrationRequest.setPhoneNumber("1234567890");
        farmerRegistrationRequest.setStreetName("John's Street");
        farmerRegistrationRequest.setStreetNumber("123");
        farmerRegistrationRequest.setProfilePicture("profile_pic.jpg");
        farmerRegistrationRequest.setState("Lagos");
        GenericResponse<FarmerResponse> genericResponse = farmerService.registerFarmer(farmerRegistrationRequest);
        assertNotNull(genericResponse);
    }



  
}