package com.semicolon.grincultified.services.userService;

import com.semicolon.grincultified.data.models.User;
import com.semicolon.grincultified.data.repositories.UserRepository;
import com.semicolon.grincultified.dtos.requests.ProfileUpdateRequest;
import com.semicolon.grincultified.dtos.responses.GenericResponse;
import com.semicolon.grincultified.dtos.responses.UserResponse;
import com.semicolon.grincultified.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static com.semicolon.grincultified.utilities.AppUtils.USER_DOES_NOT_EXIST;
import static com.semicolon.grincultified.utilities.AppUtils.USER_UPDATED_SUCCESSFULLY;

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

    @Override
    public GenericResponse<String> updateProfile(ProfileUpdateRequest profileUpdateRequest) throws UserNotFoundException {
        User user = userRepository.findByEmailAddress(profileUpdateRequest.getEmailAddress()).orElseThrow(
                ()->new UserNotFoundException(USER_DOES_NOT_EXIST));
        updateUser(user, profileUpdateRequest);
        userRepository.save(user);  
        return GenericResponse.<String>builder()
                .message(USER_UPDATED_SUCCESSFULLY)
                .build();
    }

    private void updateUser(User user, ProfileUpdateRequest profileUpdateRequest) {
        user.setFirstName(profileUpdateRequest.getFirstName());
        user.setLastName(profileUpdateRequest.getLastName());
        user.setPhoneNumber(profileUpdateRequest.getPhoneNumber());
        user.setProfilePicture(profileUpdateRequest.getProfilePicture());
    }
}
