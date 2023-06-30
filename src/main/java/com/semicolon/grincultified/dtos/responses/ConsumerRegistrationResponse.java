package com.semicolon.grincultified.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ConsumerRegistrationResponse {
    private Long id;
//    private String otp;
    private String message;
}
