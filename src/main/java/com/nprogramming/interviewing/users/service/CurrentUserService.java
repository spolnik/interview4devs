package com.nprogramming.interviewing.users.service;

import com.nprogramming.interviewing.users.domain.CurrentUser;

import java.math.BigInteger;

public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, BigInteger id);
}
