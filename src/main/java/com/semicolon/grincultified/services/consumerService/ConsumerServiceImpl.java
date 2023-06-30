package com.semicolon.grincultified.services.consumerService;

import com.semicolon.grincultified.data.models.User;
import com.semicolon.grincultified.data.repositories.ConsumerRepo;
import com.semicolon.grincultified.data.repositories.TemporaryConsumerRepo;
import com.semicolon.grincultified.dtos.requests.ConsumerRegistrationRequest;
import com.semicolon.grincultified.dtos.requests.SendMailRequest;
import com.semicolon.grincultified.dtos.responses.ConsumerRegistrationResponse;
import com.semicolon.grincultified.services.mailServices.MailService;
import com.semicolon.grincultified.services.userService.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static com.semicolon.grincultified.utilities.AppUtils.*;

@Service
@AllArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {
    private final ConsumerRepo consumerRepo;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final MailService mailService;


    @Override
    public ConsumerRegistrationResponse register(ConsumerRegistrationRequest consumerRegistrationRequest) {
        User user = modelMapper.map(consumerRegistrationRequest, User.class);
        User addedUser = userService.addUserTemporarily(user);
        sendOtp(addedUser.getEmailAddress());
        return ConsumerRegistrationResponse.builder()
                .id(addedUser.getId())
                .message(CHECK_YOUR_MAIL_FOR_YOUR_OTP)
                .build();
    }

    private void sendOtp(String emailAddress) {
        SendMailRequest sendMailRequest = new SendMailRequest();
        sendMailRequest.setFrom(SYSTEM_MAIL);
        sendMailRequest.setTo(emailAddress);
        sendMailRequest.setSubject(REGISTRATION_OTP);
        sendMailRequest.setText(OTP_MESSAGE);
        mailService.sendMail(sendMailRequest);
    }
}
