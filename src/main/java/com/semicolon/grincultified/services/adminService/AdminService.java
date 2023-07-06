package com.semicolon.grincultified.services.adminService;

import com.semicolon.grincultified.dtos.requests.AdminRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.AdminResponse;
import com.semicolon.grincultified.exception.AdminAlreadyExistException;
import com.semicolon.grincultified.exception.AdminInvitationNotFoundException;
import com.semicolon.grincultified.exception.AdminNotFoundException;

import java.util.Optional;


public interface AdminService {
    AdminResponse adminRegistration (AdminRegistrationRequest adminRegistrationRequest) throws AdminInvitationNotFoundException;

    AdminResponse findByEmail(String emailAddress) throws AdminNotFoundException;

    String validateDuplicateExistence(String emailAddress) throws AdminAlreadyExistException;

    void deleteAll();
}
