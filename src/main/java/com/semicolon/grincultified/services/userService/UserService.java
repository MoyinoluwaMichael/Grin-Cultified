package com.semicolon.grincultified.services.userService;

import com.semicolon.grincultified.dtos.requests.ProfileUpdateRequest;
import com.semicolon.grincultified.dtos.requests.ResetPasswordRequest;
import com.semicolon.grincultified.dtos.responses.GenericResponse;
import com.semicolon.grincultified.dtos.responses.UserResponse;
import com.semicolon.grincultified.exception.AuthenticationException;
import com.semicolon.grincultified.exception.InvalidEmailException;
import com.semicolon.grincultified.exception.UserNotFoundException;

public interface UserService {
    UserResponse findByEmail(String email) throws UserNotFoundException;

    GenericResponse<String> updateProfile(ProfileUpdateRequest profileUpdateRequest) throws UserNotFoundException;

    GenericResponse<String> sendResetPasswordLink(String email) throws UserNotFoundException, InvalidEmailException;

    GenericResponse<String> updatePassword(ResetPasswordRequest resetPasswordRequest)throws AuthenticationException, UserNotFoundException;
}
