package com.semicolon.grincultified.services.superAdminService;

import com.semicolon.grincultified.dtos.requests.AdminRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.GenericResponse;
import com.semicolon.grincultified.exception.*;
import org.springframework.http.ResponseEntity;

public interface SuperAdminService {
    GenericResponse<String> sendInvitationLink(String emailAddress) throws AdminInvitationNotFoundException, AdminNotFoundException, AdminAlreadyExistException, InvalidEmailException, InvestorAlreadyExistException;

    ResponseEntity<String> registerSuperAdminAccount(AdminRegistrationRequest adminRegistrationRequest) throws AuthenticationException, AdminInvitationNotFoundException;

}
