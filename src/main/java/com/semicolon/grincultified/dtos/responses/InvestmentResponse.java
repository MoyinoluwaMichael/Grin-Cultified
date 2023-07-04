package com.semicolon.grincultified.dtos.responses;

import com.semicolon.grincultified.data.models.InvestmentReturnType;
import com.semicolon.grincultified.data.models.InvestmentStatus;
import com.semicolon.grincultified.data.models.RedemptionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
public class InvestmentResponse {
    @Id
    private Long farmProjectId;
    private BigDecimal amount;
    private InvestmentReturnType returnType;
    private InvestmentStatus status;
    private RedemptionStatus redemptionStatus;
}
