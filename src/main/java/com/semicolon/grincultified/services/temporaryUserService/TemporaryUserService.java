package com.semicolon.grincultified.services.temporaryUserService;

import com.semicolon.grincultified.dtos.requests.InvestorRegistrationRequest;
import com.semicolon.grincultified.exception.TemporaryInvestorDoesNotExistException;

public interface TemporaryUserService {

    void addUserTemporarily(InvestorRegistrationRequest investorRegistrationRequest);
    InvestorRegistrationRequest findByEmailAddress(String emailAddress) throws TemporaryInvestorDoesNotExistException;
    void deleteTemporaryInvestor(InvestorRegistrationRequest investorRegistrationRequest);

    void deleteAll();

}
