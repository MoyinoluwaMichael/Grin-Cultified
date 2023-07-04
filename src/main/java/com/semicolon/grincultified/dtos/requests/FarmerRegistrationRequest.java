package com.semicolon.grincultified.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FarmerRegistrationRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private String password;
    private String landPics;
    private String profilePicture;
    private String streetName;
    private String streetNumber;
    private String city;
    private String state;
}
