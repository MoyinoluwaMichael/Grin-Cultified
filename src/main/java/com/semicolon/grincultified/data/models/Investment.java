package com.semicolon.grincultified.data.models;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jdk8.LongStreamSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long investorId;
    private Long farmProjectId;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private InvestmentReturnType returnType;
    @Enumerated(EnumType.STRING)
    private InvestmentStatus status;
    @JsonSerialize(using = LongStreamSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private RedemptionStatus redemptionStatus;

    @PrePersist
    public void localDateTime() {
        createdAt = LocalDateTime.now();
        status = InvestmentStatus.ONGOING;
        redemptionStatus = RedemptionStatus.PENDING;
    }
}
