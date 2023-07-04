package com.semicolon.grincultified.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OtpVerificationResponse {
    private String message;
}
