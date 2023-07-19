package com.semicolon.grincultified.utilities;

import com.semicolon.grincultified.data.models.Investment;
import com.semicolon.grincultified.dtos.requests.InvestmentRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.InvestmentResponse;

public class CultifyMapper {

    public Investment map(InvestmentRegistrationRequest investmentRegistrationRequest) {
        return Investment.builder()
                .amount(investmentRegistrationRequest.getAmount())
                .investorId(investmentRegistrationRequest.getInvestorId())
                .returnType(investmentRegistrationRequest.getReturnType())
                .startingDate(investmentRegistrationRequest.getStartingDate())
                .redemptionDate(investmentRegistrationRequest.getRedemptionDate())
                .farmProjectId(investmentRegistrationRequest.getFarmProjectId())
                .farmProjectName(investmentRegistrationRequest.getFarmProjectName())
                .roi(investmentRegistrationRequest.getRoi())
                .build();
    }

}
