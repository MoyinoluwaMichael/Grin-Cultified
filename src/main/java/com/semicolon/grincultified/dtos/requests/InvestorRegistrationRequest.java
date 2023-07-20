package com.semicolon.grincultified.dtos.requests;

import com.semicolon.grincultified.data.models.Otp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

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
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Otp otp;
    private LocalDateTime registrationDate;



}
