package com.semicolon.grincultified.dtos.requests;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class TransporterRegistrationRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Id
    private String emailAddress;
    private String password;
}
