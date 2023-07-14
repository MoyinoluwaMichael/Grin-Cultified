package com.semicolon.grincultified.services.userService;

import com.semicolon.grincultified.data.models.User;
import com.semicolon.grincultified.data.repositories.UserRepository;
import com.semicolon.grincultified.dtos.responses.UserResponse;
import com.semicolon.grincultified.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static com.semicolon.grincultified.utilities.AppUtils.USER_DOES_NOT_EXIST;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserResponse findByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmailAddress(email).orElseThrow(
                ()->new UserNotFoundException(USER_DOES_NOT_EXIST));
        return modelMapper.map(user, UserResponse.class);
    }
}
