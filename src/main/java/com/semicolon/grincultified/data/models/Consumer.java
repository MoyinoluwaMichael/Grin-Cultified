package com.semicolon.grincultified.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Consumer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;
    @OneToOne
    private Payment payment;
    @OneToMany
    private List<FarmProduce> farmProduce;
    @OneToOne
    private Transaction transaction;
    @OneToOne
    private OrderTracking orderTracking;
}
