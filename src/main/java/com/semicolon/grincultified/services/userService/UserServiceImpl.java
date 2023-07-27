package com.semicolon.grincultified.services.userService;

import com.semicolon.grincultified.data.models.User;
import com.semicolon.grincultified.data.repositories.UserRepository;
import com.semicolon.grincultified.dtos.requests.ProfileUpdateRequest;
import com.semicolon.grincultified.dtos.requests.ResetPasswordRequest;
import com.semicolon.grincultified.dtos.requests.SendMailRequest;
import com.semicolon.grincultified.dtos.responses.GenericResponse;
import com.semicolon.grincultified.dtos.responses.UserResponse;
import com.semicolon.grincultified.exception.AuthenticationException;
import com.semicolon.grincultified.exception.InvalidEmailException;
import com.semicolon.grincultified.exception.UserNotFoundException;
import com.semicolon.grincultified.services.mailService.MailService;
import com.semicolon.grincultified.utilities.JwtUtility;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.semicolon.grincultified.utilities.AppUtils.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private MailService mailService;
    private final JwtUtility jwtUtility;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse findByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmailAddress(email).orElseThrow(
                () -> new UserNotFoundException(USER_DOES_NOT_EXIST));
        return modelMapper.map(user, UserResponse.class);
    }


    @Override
    public GenericResponse<String> updateProfile(ProfileUpdateRequest profileUpdateRequest) throws UserNotFoundException {
        User user = userRepository.findByEmailAddress(profileUpdateRequest.getEmailAddress()).orElseThrow(
                () -> new UserNotFoundException(USER_DOES_NOT_EXIST));
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

    @Override
    public GenericResponse<String> sendResetPasswordLink(String emailAddress) throws UserNotFoundException, InvalidEmailException {
        userRepository.findByEmailAddress(emailAddress).orElseThrow(() -> new UserNotFoundException(USER_DOES_NOT_EXIST));
        String encryptedEmail = jwtUtility.generateEncryptedLink(emailAddress);
        try {
            sendResetPasswordEmail(encryptedEmail, emailAddress);
        } catch (Exception e) {
            throw new InvalidEmailException(INVALID_EMAIL);
        }
        return GenericResponse.<String>builder()
                .status(OK)
                .message(RESET_PASSWORD_EMAIL_SENT_SUCCESSFULLY)
                .build();
    }

    private void sendResetPasswordEmail(String encryptedEmail, String emailAddress) {
        SendMailRequest sendMailRequest = new SendMailRequest();
        sendMailRequest.setSubject(RESET_PASSWORD);
        sendMailRequest.setTo(emailAddress);
        sendMailRequest.setFrom(SYSTEM_MAIL);
        String link = "http://localhost:3000/resetPassword/"+encryptedEmail;
        sendMailRequest.setText(String.format(RESET_PASSWORD_MAIL_BODY, link));
        mailService.sendMail(sendMailRequest);
    }

    @Override
    public GenericResponse<String> updatePassword(ResetPasswordRequest resetPasswordRequest) throws AuthenticationException, UserNotFoundException {
        String email = jwtUtility.extractEmailFrom(resetPasswordRequest.getToken());
        if (email != null){
            User user = userRepository.findByEmailAddress(email).orElseThrow(() -> new UserNotFoundException(USER_DOES_NOT_EXIST));
            user.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
            userRepository.save(user);
            return GenericResponse.<String>builder()
                    .status(OK)
                    .message(PASSWORD_UPDATE_SUCCESSFUL)
                    .build();
        }
        else {
            throw new AuthenticationException(INVALID_TOKEN);
        }
    }

}
