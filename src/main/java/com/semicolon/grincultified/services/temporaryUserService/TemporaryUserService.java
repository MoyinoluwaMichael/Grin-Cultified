package com.semicolon.grincultified.services.temporaryUserService;

import com.semicolon.grincultified.dtos.requests.InvestorRegistrationRequest;
import com.semicolon.grincultified.exception.TemporaryInvestorDoesNotExistException;

public interface TemporaryUserService {
<<<<<<< HEAD

    void addUserTemporarily(InvestorRegistrationRequest investorRegistrationRequest);
    InvestorRegistrationRequest findByEmailAddress(String emailAddress) throws TemporaryInvestorDoesNotExistException;
=======
   void addUserTemporarily(InvestorRegistrationRequest investorRegistrationRequest);
    InvestorRegistrationRequest findByEmail(String emailAddress) throws TemporaryInvestorDoesNotExistException;
>>>>>>> b3c6df1ac12837e75a98a6fbed9bbc49f33156c9
    void deleteTemporaryInvestor(InvestorRegistrationRequest investorRegistrationRequest);
    void validateDuplicateTemporaryInvestor(String emailAddress);

    void deleteAll();

}
