package com.semicolon.grincultified.data.models;

import jakarta.persistence.Entity;


import java.time.LocalDateTime;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private int roi;
    private InvestmentType investmentType;
    private LocalDateTime startDate;
    private LocalDateTime maturityDate;
    private  String payOutType;


}
