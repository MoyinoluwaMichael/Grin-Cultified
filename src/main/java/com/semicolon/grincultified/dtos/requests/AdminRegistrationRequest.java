package com.semicolon.grincultified.dtos.requests;

import com.semicolon.grincultified.data.models.Address;
import com.semicolon.grincultified.data.models.AdminType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminRegistrationRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private AdminType adminType;
    private String streetName;
    private String streetNumber;
    private String city;
    private String state;
    private String emailAddress;
    private String password;
}
