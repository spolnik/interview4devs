package com.nprogramming.interviewing.users.service;

import com.nprogramming.interviewing.users.domain.CurrentUser;
import com.nprogramming.interviewing.users.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(CurrentUserDetailsService.class);
    private final UserService userService;

    @Autowired
    public CurrentUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LOG.debug("Authenticating user with email={}", email.replaceFirst("@.*", "@***"));

        User user = userService.getUserByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                String.format("User with email=%s was not found", email)
                        )
                );

        return new CurrentUser(user);
    }
}
