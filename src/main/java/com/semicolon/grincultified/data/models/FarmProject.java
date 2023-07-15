package com.semicolon.grincultified.data.models;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.sql.results.spi.LoadContexts;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FarmProject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String farmProduceSummary;
    private String description;
    private Long numberOfInvestors;
    private BigDecimal pricePerUnit;
    private String location;
    @Enumerated(EnumType.STRING)
    private FarmProjectStatus status;
    private String pictures;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private InvestmentPlan investmentPlan;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime uploadedAt;

    @PrePersist
    public void setFirst(){
        status = FarmProjectStatus.AVAILABLE;
        uploadedAt = LocalDateTime.now();
    }

}
