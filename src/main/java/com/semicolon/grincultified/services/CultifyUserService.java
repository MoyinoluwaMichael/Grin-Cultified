package com.semicolon.grincultified.services;

import com.semicolon.grincultified.data.models.User;
import com.semicolon.grincultified.data.repositories.UserRepository;
import com.semicolon.grincultified.security.cultifyUser.CultifyUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import static com.semicolon.grincultified.utilities.AppUtils.USER_WITH_EMAIL_NOT_FOUND;


@AllArgsConstructor
@Repository
public class CultifyUserService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmailAddress(email).orElseThrow(()->new UsernameNotFoundException(
                String.format(USER_WITH_EMAIL_NOT_FOUND, email)
        ));
        return new CultifyUser(user);
    }
}
