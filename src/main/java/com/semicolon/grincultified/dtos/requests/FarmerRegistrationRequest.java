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
    private String profilePicture;
    private String location;
    private String specialization;
    private String description;
}
