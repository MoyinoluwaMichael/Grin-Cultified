package com.semicolon.grincultified.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SendMailRequest {
    private String to;
    private String sender;
    private String subject;
    private String message;
}
