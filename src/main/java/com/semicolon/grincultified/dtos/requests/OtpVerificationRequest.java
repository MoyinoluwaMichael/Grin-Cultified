package com.semicolon.grincultified.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OtpVerificationRequest {
    private String otp;
    private String emailAddress;
}
