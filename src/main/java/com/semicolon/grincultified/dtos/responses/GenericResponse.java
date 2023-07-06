package com.semicolon.grincultified.dtos.responses;

import lombok.Builder;

@Builder
public class GenericResponse<T>{
    private String status;
    private String message;
    private T data;
}
