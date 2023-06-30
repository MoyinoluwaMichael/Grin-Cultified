package com.semicolon.grincultified.services.userService;

import com.semicolon.grincultified.data.models.User;
import com.semicolon.grincultified.data.repositories.UserRepo;

public interface UserService {
    User addUserTemporarily(User user);
}
