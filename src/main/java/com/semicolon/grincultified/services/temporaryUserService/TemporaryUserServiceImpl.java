package com.semicolon.grincultified.services.temporaryUserService;

import com.semicolon.grincultified.data.repositories.TemporaryUserRepo;
import com.semicolon.grincultified.dtos.requests.InvestorRegistrationRequest;
import com.semicolon.grincultified.exception.TemporaryInvestorDoesNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.semicolon.grincultified.utilities.AppUtils.INITIAL_REGISTRATION_NOT_FOUND_FOR;

@Service
@AllArgsConstructor
public class TemporaryUserServiceImpl implements TemporaryUserService {
    private final TemporaryUserRepo temporaryUserRepo;

    @Override
    public void addUserTemporarily(InvestorRegistrationRequest investorRegistrationRequest) {
        temporaryUserRepo.save(investorRegistrationRequest);
    }

    @Override
    public InvestorRegistrationRequest findByEmailAddress(String emailAddress) throws TemporaryInvestorDoesNotExistException {
        return temporaryUserRepo.findByEmailAddress(emailAddress)
                                .orElseThrow(() -> new TemporaryInvestorDoesNotExistException(
                                                String.format(INITIAL_REGISTRATION_NOT_FOUND_FOR, emailAddress)));
    }
    @Override
    public void deleteTemporaryInvestor(InvestorRegistrationRequest investorRegistrationRequest){
        temporaryUserRepo.delete(investorRegistrationRequest);
    }

    @Override
    public void deleteAll() {
        temporaryUserRepo.deleteAll();
    }
}
