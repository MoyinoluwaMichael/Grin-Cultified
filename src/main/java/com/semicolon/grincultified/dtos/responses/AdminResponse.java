package com.semicolon.grincultified.dtos.responses;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.semicolon.grincultified.data.models.Address;
import com.semicolon.grincultified.data.models.AdminType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class AdminResponse {
    private Long id;
    private UserResponse userResponse;
    private AdminType adminType;

    @Override
    public String toString() {
        return "AdminResponse{" +
                "id=" + id +
                ", userResponse=" + userResponse +
                ", adminType=" + adminType +
                '}';
    }
}
