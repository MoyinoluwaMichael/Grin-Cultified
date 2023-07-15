package com.semicolon.grincultified.services.adminInvitationService;

import com.semicolon.grincultified.data.models.AdminInvitation;
import com.semicolon.grincultified.data.repositories.AdminInvitationRepository;
import com.semicolon.grincultified.exception.AdminInvitationNotFoundException;
import com.semicolon.grincultified.exception.AuthenticationException;
import com.semicolon.grincultified.utilities.JwtUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.semicolon.grincultified.utilities.AppUtils.*;

@Service
@AllArgsConstructor
public class AdminInvitationServiceImpl implements AdminInvitationService {
    private final AdminInvitationRepository adminInvitationRepository;
    private final JwtUtility jwtUtility;

    @Override
    public Optional<AdminInvitation> findByEmail(String emailAddress) {
        return adminInvitationRepository.findByEmail(emailAddress);
    }

    @Override
    public String registerInvitation(AdminInvitation invitation) {
        if (findByEmail(invitation.getEmail()).isEmpty()) {
            adminInvitationRepository.save(invitation);
            return INVITATION_REGISTERED;
        }
        return INVITATION_ALREADY_REGISTERED;
    }

    @Override
    public String verifyInvitationForRegistration(String emailAddress) throws AdminInvitationNotFoundException, AuthenticationException {
        String email = jwtUtility.extractEmailFrom(emailAddress);
        findByEmail(email).orElseThrow(()->new AdminInvitationNotFoundException(ADMIN_INVITATION_NOT_FOUND));
        return email;
    }

    @Override
    public void deleteAll() {
        adminInvitationRepository.deleteAll();
    }
}
