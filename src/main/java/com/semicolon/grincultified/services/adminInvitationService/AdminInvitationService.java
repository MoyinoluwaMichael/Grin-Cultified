package com.semicolon.grincultified.services.adminInvitationService;

import com.semicolon.grincultified.data.models.AdminInvitation;
import com.semicolon.grincultified.dtos.responses.GenericResponse;
import com.semicolon.grincultified.exception.AdminInvitationNotFoundException;
import com.semicolon.grincultified.exception.AuthenticationException;

import java.util.List;
import java.util.Optional;

public interface AdminInvitationService {
    Optional<AdminInvitation> findByEmail(String emailAddress);

    String registerInvitation(AdminInvitation invitation);

    String verifyInvitationForRegistration(String emailAddress) throws AdminInvitationNotFoundException, AuthenticationException;

    void deleteAll();

    GenericResponse<List<AdminInvitation>> getAllAdministratorsPendingInvitation();
}
