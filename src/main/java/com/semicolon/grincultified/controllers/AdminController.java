package com.semicolon.grincultified.controllers;

import com.semicolon.grincultified.dtos.requests.AdminRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.AdminResponse;
import com.semicolon.grincultified.exception.AdminInvitationNotFoundException;
import com.semicolon.grincultified.exception.AuthenticationException;
import com.semicolon.grincultified.services.adminService.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/semicolon/cultify/v1/api/admin")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/registration")
    public ResponseEntity<AdminResponse> register(@RequestBody AdminRegistrationRequest adminRegistrationRequest) throws AdminInvitationNotFoundException, AuthenticationException {
        return adminService.register(adminRegistrationRequest);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<AdminResponse>> findAll() {
        return adminService.findAll();
    }
}
