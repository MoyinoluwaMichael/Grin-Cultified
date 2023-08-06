package com.semicolon.grincultified.controllers;

import com.semicolon.grincultified.dtos.requests.AdminRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.AdminResponse;
import com.semicolon.grincultified.exception.AdminInvitationNotFoundException;
import com.semicolon.grincultified.exception.AuthenticationException;
import com.semicolon.grincultified.services.adminService.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.semicolon.grincultified.utilities.AppUtils.*;

@RestController
@AllArgsConstructor
@RequestMapping("/semicolon/cultify/v1/api/admin")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/registration")
    public ResponseEntity<Map<String, Object>> register(@RequestBody AdminRegistrationRequest adminRegistrationRequest) throws AdminInvitationNotFoundException, AuthenticationException {
        return adminService.register(adminRegistrationRequest);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<AdminResponse>> findAll() {
        return adminService.findAll();
    }
}
