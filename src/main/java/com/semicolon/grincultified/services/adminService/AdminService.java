package com.semicolon.grincultified.services.adminService;

import com.semicolon.grincultified.dtos.requests.AdminRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.AdminResponse;
import com.semicolon.grincultified.exception.AdminAlreadyExistException;
import com.semicolon.grincultified.exception.AdminInvitationNotFoundException;
import com.semicolon.grincultified.exception.AuthenticationException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;


public interface AdminService {
    ResponseEntity<Map<String, Object>> register(AdminRegistrationRequest adminRegistrationRequest) throws AdminInvitationNotFoundException, AuthenticationException;

    AdminResponse findByEmail(String emailAddress);

    String validateDuplicateExistence(String emailAddress) throws AdminAlreadyExistException;

    void deleteAll();

    Long count();

    ResponseEntity<List<AdminResponse>> findAll();

}
