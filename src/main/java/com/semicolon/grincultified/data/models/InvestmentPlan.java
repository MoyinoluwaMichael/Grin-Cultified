package com.semicolon.grincultified.data.models;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvestmentPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  int roi;
    @Enumerated(EnumType.STRING)

    private InvestmentType investmentType;
    private LocalDateTime startDate;
    private  LocalDateTime maturityDate;
    private  String payOutType;




}
