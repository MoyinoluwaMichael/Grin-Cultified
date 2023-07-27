package com.semicolon.grincultified.dtos.requests;

import com.semicolon.grincultified.data.models.Otp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class InvestorRegistrationRequest {
    @Id
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
}
