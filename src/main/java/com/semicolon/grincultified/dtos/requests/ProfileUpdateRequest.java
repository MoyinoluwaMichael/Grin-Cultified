package com.semicolon.grincultified.dtos.requests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileUpdateRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private String profilePicture;
}
