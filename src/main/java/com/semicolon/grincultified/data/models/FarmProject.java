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

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;


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
    private int numberOfUnitInvestedSoFar;
    @Enumerated(EnumType.STRING)
    private FarmProjectStatus status;
    private String picture;
    private String location;
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
