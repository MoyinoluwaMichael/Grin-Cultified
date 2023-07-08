package com.semicolon.grincultified.security.manager;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import static com.semicolon.grincultified.utilities.AppUtils.AUTHENTICATION_FAILED_FOR_USER_WITH_EMAIL;


@Component
@AllArgsConstructor
public class CultifyAuthenticationManager implements AuthenticationManager {
    private final AuthenticationProvider authenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication authResult;
        String email = (String) authentication.getPrincipal();
        if (authenticationProvider.supports(authentication.getClass())) {
            authResult = authenticationProvider.authenticate(authentication);
            return authResult;
        } else throw new BadCredentialsException(String.format(AUTHENTICATION_FAILED_FOR_USER_WITH_EMAIL, email));
    }
}
