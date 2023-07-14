package com.semicolon.grincultified.services.userService;

import com.semicolon.grincultified.dtos.responses.UserResponse;
import com.semicolon.grincultified.exception.UserNotFoundException;

public interface UserService {
    UserResponse findByEmail(String email) throws UserNotFoundException;
}
