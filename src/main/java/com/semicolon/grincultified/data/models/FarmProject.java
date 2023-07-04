package com.semicolon.grincultified.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.sql.results.spi.LoadContexts;

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
    private  String farmProduceSummary;
    private BigDecimal description;
    private Long numberOfInvestors;
    private  FarmProduceStatus status;
    private  String picture;
    @OneToOne
    private InvestmentPlan investmentPlan;
    private LocalDateTime uploadedAt;

}
