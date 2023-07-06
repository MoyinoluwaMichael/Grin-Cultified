package com.semicolon.grincultified.dtos.responses;


import com.semicolon.grincultified.data.models.BankAccount;
import com.semicolon.grincultified.data.models.Investment;
import com.semicolon.grincultified.data.models.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import lombok.*;


@Setter
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class InvestorResponse {
    private Long id;
    private UserResponse userResponse;
    private List<Investment> investments;
    private BankAccount bankAccount;
}
