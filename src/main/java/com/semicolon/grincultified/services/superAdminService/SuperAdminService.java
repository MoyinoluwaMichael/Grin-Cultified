package com.semicolon.grincultified.services.superAdminService;

import com.semicolon.grincultified.dtos.responses.GenericResponse;

public interface SuperAdminService {
    GenericResponse<String> sendInvitationLink(String emailAddress);
}
