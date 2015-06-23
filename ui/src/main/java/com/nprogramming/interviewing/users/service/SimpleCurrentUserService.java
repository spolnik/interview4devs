package com.nprogramming.interviewing.users.service;

import com.hiringdefined.domain.CurrentUser;
import com.hiringdefined.domain.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class SimpleCurrentUserService implements CurrentUserService {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleCurrentUserService.class);

    @Override
    public boolean canAccessUser(CurrentUser currentUser, BigInteger userId) {

        LOG.debug("Checking if user={} has access to user={}", currentUser, userId);

        return currentUser != null && currentUser.getId() != null
                && (currentUser.getRole()) == Role.ADMIN || currentUser.getId().equals(userId);
    }
}
