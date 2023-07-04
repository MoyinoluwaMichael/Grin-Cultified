package com.semicolon.grincultified.data.models;


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
    private FarmProjectStatus status;
    private String pictures;
    @OneToOne
    private InvestmentPlan investmentPlan;
    private LocalDateTime uploadedAt;

}
