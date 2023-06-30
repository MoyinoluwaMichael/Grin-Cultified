package com.semicolon.grincultified.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SendMailRequest {
    private String to;
    private String from;
    private String subject;
    private String text;
}
