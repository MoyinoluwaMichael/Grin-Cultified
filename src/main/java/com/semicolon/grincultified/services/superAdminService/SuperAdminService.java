package com.semicolon.grincultified.services.superAdminService;

import com.semicolon.grincultified.dtos.responses.GenericResponse;
import com.semicolon.grincultified.exception.*;

public interface SuperAdminService {
    GenericResponse<String> sendInvitationLink(String emailAddress) throws AdminInvitationNotFoundException, AdminNotFoundException, AdminAlreadyExistException, InvalidEmailException, InvestorAlreadyExistException;
}
