package com.semicolon.grincultified.services.userService;

import com.semicolon.grincultified.data.models.User;
import com.semicolon.grincultified.data.repositories.TemporaryUserRepo;
import com.semicolon.grincultified.data.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;
    private final TemporaryUserRepo temporaryUserRepo;

    @Override
    public User addUserTemporarily(User user) {
        return temporaryUserRepo.save(user);
    }
}
