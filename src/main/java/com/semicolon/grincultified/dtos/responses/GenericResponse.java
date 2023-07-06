package com.semicolon.grincultified.dtos.responses;

import lombok.*;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class GenericResponse<T>{
    private String status;
    private String message;
    private T data;
}
