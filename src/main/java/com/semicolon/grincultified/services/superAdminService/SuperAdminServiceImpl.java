package com.semicolon.grincultified.services.superAdminService;

import com.semicolon.grincultified.dtos.requests.SendMailRequest;
import com.semicolon.grincultified.dtos.responses.GenericResponse;
import com.semicolon.grincultified.services.mailService.MailService;
import com.semicolon.grincultified.utilities.JwtUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.semicolon.grincultified.utilities.AppUtils.*;

@AllArgsConstructor
@Service
public class SuperAdminServiceImpl implements SuperAdminService{
    private final MailService mailService;

    @Override
    public GenericResponse<String> sendInvitationLink(String emailAddress) {
        String encryptedEmail = JwtUtility.generateEncryptedLink(emailAddress);
        String invitationLink = ADMIN_REGISTRATION_PAGE_URL.concat(encryptedEmail);
        SendMailRequest sendMailRequest = new SendMailRequest();
        sendMailRequest.setFrom(SYSTEM_MAIL);
        sendMailRequest.setTo(emailAddress);
        sendMailRequest.setSubject(CULTIFY_ADMIN_INVITATION);
        sendMailRequest.setText(String.format(ADMIN_INVITATION_MAIL_TEMPLATE, emailAddress, invitationLink));
        mailService.sendMail(sendMailRequest);
        return GenericResponse.<String>builder()
                .status(OK)
                .message(INVITATION_SENT_SUCCESSFULLY)
                .build();
    }
}
