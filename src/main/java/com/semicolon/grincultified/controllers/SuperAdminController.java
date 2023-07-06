package com.semicolon.grincultified.controllers;

import com.semicolon.grincultified.dtos.responses.GenericResponse;
import com.semicolon.grincultified.services.superAdminService.SuperAdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/semicolon/cultify/v1/api/superAdmin")
public class SuperAdminController {
    private final SuperAdminService superAdminService;

    @PostMapping("/sendInvitationLink/{emailAddress}")
    public ResponseEntity<GenericResponse<String>> sendInvitationLink(@PathVariable String emailAddress){
        return ResponseEntity.ok().body(superAdminService.sendInvitationLink(emailAddress));
    }
}
