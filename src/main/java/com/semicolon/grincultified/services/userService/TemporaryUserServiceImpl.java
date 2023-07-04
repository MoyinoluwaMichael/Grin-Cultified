package com.semicolon.grincultified.services.userService;

import com.semicolon.grincultified.data.repositories.TemporaryUserRepo;
import com.semicolon.grincultified.data.repositories.UserRepo;
import com.semicolon.grincultified.dtos.requests.InvestorRegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TemporaryUserServiceImpl implements TemporaryUserService {
    private final TemporaryUserRepo temporaryUserRepo;

    @Override
    public void addUserTemporarily(InvestorRegistrationRequest investorRegistrationRequest) {
        temporaryUserRepo.save(investorRegistrationRequest);
    }

    @Override
    public InvestorRegistrationRequest findByEmailAddress(String emailAddress) {
        InvestorRegistrationRequest investorRegistrationRequest = temporaryUserRepo.findByEmailAddress(emailAddress)
                .orElseThrow(() -> new RuntimeException("User doesnt exist"));
        return investorRegistrationRequest;
    }
    @Override
    public void deleteTemporaryInvestor(InvestorRegistrationRequest investorRegistrationRequest){
        temporaryUserRepo.delete(investorRegistrationRequest);
    }
}
