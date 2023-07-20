package com.semicolon.grincultified.controllers;

import com.semicolon.grincultified.data.models.AdminInvitation;
import com.semicolon.grincultified.dtos.responses.GenericResponse;
import com.semicolon.grincultified.exception.*;
import com.semicolon.grincultified.services.adminInvitationService.AdminInvitationService;
import com.semicolon.grincultified.services.superAdminService.SuperAdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/semicolon/cultify/v1/api/superAdmin")
public class SuperAdminController {
    private final SuperAdminService superAdminService;
    private final AdminInvitationService adminInvitationService;

    @PostMapping("/sendInvitationLink/{emailAddress}")
    public ResponseEntity<GenericResponse<String>> sendInvitationLink(@PathVariable String emailAddress) throws AdminAlreadyExistException, AdminInvitationNotFoundException, AdminNotFoundException, InvalidEmailException, InvestorAlreadyExistException {
        return ResponseEntity.ok().body(superAdminService.sendInvitationLink(emailAddress));
    }

    @GetMapping("/getAllAdministratorsPendingInvitation")
    public ResponseEntity<GenericResponse<List<AdminInvitation>>> getAllAdministratorsPendingInvitation() {
        return ResponseEntity.ok().body(adminInvitationService.getAllAdministratorsPendingInvitation());
    }


}
