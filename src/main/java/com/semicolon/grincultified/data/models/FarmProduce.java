package com.semicolon.grincultified.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FarmProduce {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String picture;
    private String name;
    private BigDecimal amount;
    private UOM uom;
    private int quantity;
    private String location;
}


