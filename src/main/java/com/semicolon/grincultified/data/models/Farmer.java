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
public class Farmer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private User user;
    private String landPics;
    @OneToMany
    private List<FarmProduce> farmProduces;
    @OneToOne
    private Transaction transaction;
    @OneToOne
    private OrderTracking trackOrder;
}

