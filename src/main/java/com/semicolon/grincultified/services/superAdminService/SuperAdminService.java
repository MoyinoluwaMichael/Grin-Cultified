package com.semicolon.grincultified.services.superAdminService;

import com.semicolon.grincultified.dtos.responses.GenericResponse;
import com.semicolon.grincultified.exception.AdminAlreadyExistException;
import com.semicolon.grincultified.exception.AdminInvitationNotFoundException;
import com.semicolon.grincultified.exception.AdminNotFoundException;

public interface SuperAdminService {
    GenericResponse<String> sendInvitationLink(String emailAddress) throws AdminInvitationNotFoundException, AdminNotFoundException, AdminAlreadyExistException;
}
