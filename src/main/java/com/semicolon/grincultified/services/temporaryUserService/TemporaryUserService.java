package com.semicolon.grincultified.services.temporaryUserService;

import com.semicolon.grincultified.dtos.requests.InvestorRegistrationRequest;

public interface TemporaryUserService {
    void addUserTemporarily(InvestorRegistrationRequest investorRegistrationRequest);
    InvestorRegistrationRequest findByEmailAddress(String emailAddress);
    void deleteTemporaryInvestor(InvestorRegistrationRequest investorRegistrationRequest);
}
