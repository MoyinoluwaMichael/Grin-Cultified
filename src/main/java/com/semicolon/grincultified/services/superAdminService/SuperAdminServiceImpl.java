package com.semicolon.grincultified.services.superAdminService;

import com.semicolon.grincultified.data.models.Admin;
import com.semicolon.grincultified.data.models.AdminInvitation;
import com.semicolon.grincultified.dtos.requests.SendMailRequest;
import com.semicolon.grincultified.dtos.responses.AdminResponse;
import com.semicolon.grincultified.dtos.responses.GenericResponse;
import com.semicolon.grincultified.exception.AdminAlreadyExistException;
import com.semicolon.grincultified.exception.AdminInvitationNotFoundException;
import com.semicolon.grincultified.exception.AdminNotFoundException;
import com.semicolon.grincultified.services.adminInvitationService.AdminInvitationService;
import com.semicolon.grincultified.services.adminService.AdminService;
import com.semicolon.grincultified.services.mailService.MailService;
import com.semicolon.grincultified.utilities.JwtUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.semicolon.grincultified.utilities.AppUtils.*;

@Service
@AllArgsConstructor
public class SuperAdminServiceImpl implements SuperAdminService{
    private final MailService mailService;
    private final AdminInvitationService adminInvitationService;
    private final AdminService adminService;
    private final JwtUtility jwtUtility;

    @Override
    public GenericResponse<String> sendInvitationLink(String emailAddress) throws AdminAlreadyExistException {
        adminService.validateDuplicateExistence(emailAddress);
        AdminInvitation invitation = new AdminInvitation();
        invitation.setEmail(emailAddress);
        adminInvitationService.registerInvitation(invitation);
        String encryptedEmail = jwtUtility.generateEncryptedLink(emailAddress);
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
