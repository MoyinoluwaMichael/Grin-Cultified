package com.semicolon.grincultified.dtos.requests;

import com.semicolon.grincultified.data.models.Address;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class ConsumerRegistrationRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Id
    private String emailAddress;
    private String password;
}
