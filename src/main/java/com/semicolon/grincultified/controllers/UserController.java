package com.semicolon.grincultified.controllers;

import com.semicolon.grincultified.dtos.requests.ProfileUpdateRequest;
import com.semicolon.grincultified.dtos.requests.ResetPasswordRequest;
import com.semicolon.grincultified.dtos.responses.GenericResponse;
import com.semicolon.grincultified.exception.*;
import com.semicolon.grincultified.services.userService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/semicolon/cultify/v1/api/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PatchMapping("/updateProfile")
    public ResponseEntity<GenericResponse<String>> updateProfile(@RequestBody ProfileUpdateRequest profileUpdateRequest) throws AdminAlreadyExistException, AdminInvitationNotFoundException, AdminNotFoundException, InvalidEmailException, InvestorAlreadyExistException, UserNotFoundException {
        return ResponseEntity.ok().body(userService.updateProfile(profileUpdateRequest));
    }

    @PostMapping("/sendResetPasswordLink/{emailAddress}")
    public ResponseEntity<GenericResponse<String>> sendResetPasswordLink(@PathVariable String emailAddress)  throws UserNotFoundException, InvalidEmailException {
        return ResponseEntity.ok().body(userService.sendResetPasswordLink(emailAddress));
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<GenericResponse<String>> updatePassword(@RequestBody ResetPasswordRequest resetPasswordRequest)throws AuthenticationException, UserNotFoundException{
        return ResponseEntity.ok().body(userService.updatePassword(resetPasswordRequest));
    }

}
