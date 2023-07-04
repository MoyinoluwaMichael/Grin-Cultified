package com.semicolon.grincultified.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class Waybill {
    private  int id;
    @OneToMany
    private Address from;
    private Address to;
    private List<Transaction> transaction;
    private  DeliveryStatus status;
    private  Payment payment;
    private LocalDateTime createdAt;
}
