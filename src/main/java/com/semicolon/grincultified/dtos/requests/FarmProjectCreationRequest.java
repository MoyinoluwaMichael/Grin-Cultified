package com.semicolon.grincultified.dtos.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.semicolon.grincultified.data.models.InvestmentType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Setter
@Getter
public class FarmProjectCreationRequest {
    private String farmProduceSummary;
    private String description;
    private String picture;
    private String location;
    private int maximumNumberOfUnit;
    private BigDecimal amountPerUnit;
    private int roi;
    private InvestmentType investmentType;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate maturityDate;
    private String payoutType;
}