package com.semicolon.grincultified.services.temporaryUserService;

import com.semicolon.grincultified.data.repositories.TemporaryInvestorRepository;
import com.semicolon.grincultified.dtos.requests.InvestorRegistrationRequest;
import com.semicolon.grincultified.exception.TemporaryInvestorDoesNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.semicolon.grincultified.utilities.AppUtils.*;

@Service
@AllArgsConstructor
public class TemporaryUserServiceImpl implements TemporaryUserService {
    private final TemporaryInvestorRepository temporaryInvestorRepository;

    @Override
    public void addUserTemporarily(InvestorRegistrationRequest investorRegistrationRequest) {
        temporaryInvestorRepository.save(investorRegistrationRequest);
    }

    @Override
    public InvestorRegistrationRequest findByEmail(String emailAddress) throws TemporaryInvestorDoesNotExistException {
        return temporaryInvestorRepository.findByEmailAddress(emailAddress).orElseThrow(()->new TemporaryInvestorDoesNotExistException(
                                                String.format(INITIAL_REGISTRATION_NOT_FOUND_FOR, emailAddress)));
    }

    @Override
    public void validateDuplicateTemporaryInvestor(String emailAddress) {
        var tempInvestor = temporaryInvestorRepository.findByEmailAddress(emailAddress);
        tempInvestor.ifPresent(this::deleteTemporaryInvestor);
    }
    @Override
    public void deleteTemporaryInvestor(InvestorRegistrationRequest investorRegistrationRequest){
        temporaryInvestorRepository.delete(investorRegistrationRequest);
    }

    @Override
    public void deleteAll() {
        temporaryInvestorRepository.deleteAll();
    }
}
