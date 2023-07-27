package com.semicolon.grincultified.services.investorService;

import com.semicolon.grincultified.dtos.requests.InvestorRegistrationRequest;
import com.semicolon.grincultified.dtos.requests.CompleteRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.GenericResponse;
import com.semicolon.grincultified.dtos.responses.InvestorResponse;
import com.semicolon.grincultified.exception.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface InvestorService {
    ResponseEntity<GenericResponse<String>> initiateRegistration(InvestorRegistrationRequest investorRegistrationRequest) throws DuplicateInvestorException, TemporaryInvestorDoesNotExistException, AdminAlreadyExistException, InvalidEmailException;
    ResponseEntity<Map<String, Object>> confirmRegistration(CompleteRegistrationRequest completeRegistrationRequest) throws TemporaryInvestorDoesNotExistException, InvalidOtpException, InvestorRegistrationFailed;

    InvestorResponse findByEmail(String email);

    InvestorResponse findById(Long investorId);

    ResponseEntity<List<InvestorResponse>> getAllInvestors();

    void deleteAll();

    Long count();

    String validateDuplicateExistence(String emailAddress) throws InvestorAlreadyExistException;
}
