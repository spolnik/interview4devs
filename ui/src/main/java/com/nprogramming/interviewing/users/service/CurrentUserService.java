package com.nprogramming.interviewing.users.service;

import com.hiringdefined.domain.CurrentUser;

import java.math.BigInteger;

public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, BigInteger id);
}
