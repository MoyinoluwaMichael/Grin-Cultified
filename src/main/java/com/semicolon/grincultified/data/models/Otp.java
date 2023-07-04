package com.semicolon.grincultified.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String otpToken;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private LocalDateTime verifiedAt;

}
