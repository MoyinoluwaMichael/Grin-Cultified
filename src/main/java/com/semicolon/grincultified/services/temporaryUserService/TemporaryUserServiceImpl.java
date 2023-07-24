package com.semicolon.grincultified.services.temporaryUserService;

import com.semicolon.grincultified.data.repositories.TemporaryInvestorRepository;
import com.semicolon.grincultified.dtos.requests.InvestorRegistrationRequest;
import com.semicolon.grincultified.exception.TemporaryInvestorDoesNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static com.semicolon.grincultified.utilities.AppUtils.*;

@Service
@AllArgsConstructor
public class TemporaryUserServiceImpl implements TemporaryUserService {
    private final TemporaryInvestorRepository temporaryInvestorRepository;

    @Override
    public void addUserTemporarily(InvestorRegistrationRequest investorRegistrationRequest) {
        investorRegistrationRequest.setRegistrationDate(LocalDateTime.now());
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
    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteExpiredTemporaryInvestor() {
        List<InvestorRegistrationRequest> temporaryInvestors = temporaryInvestorRepository.findAll();
        LocalDate twoDaysAgo = LocalDate.now().minusDays(2);

        for (InvestorRegistrationRequest investors : temporaryInvestors) {
            LocalDate registrationDate = investors.getRegistrationDate().toLocalDate();
            if (registrationDate.isBefore(twoDaysAgo)) {
                deleteTemporaryInvestor(investors);

            }
        }
    }



    @Override
    public void deleteAll() {
        temporaryInvestorRepository.deleteAll();
    }
}
