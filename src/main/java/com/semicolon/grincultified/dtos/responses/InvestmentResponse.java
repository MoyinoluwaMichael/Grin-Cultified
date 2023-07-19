package com.semicolon.grincultified.dtos.responses;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jdk8.LongStreamSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.semicolon.grincultified.data.models.InvestmentReturnType;
import com.semicolon.grincultified.data.models.InvestmentStatus;
import com.semicolon.grincultified.data.models.RedemptionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class InvestmentResponse {
    private Long id;
    private Long investorId;
    private Long farmProjectId;
    private String farmProjectName;
    private int roi;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private InvestmentReturnType returnType;
    @Enumerated(EnumType.STRING)
    private InvestmentStatus status;
    @Enumerated(EnumType.STRING)
    private RedemptionStatus redemptionStatus;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startingDate;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate redemptionDate;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdAt;
}
