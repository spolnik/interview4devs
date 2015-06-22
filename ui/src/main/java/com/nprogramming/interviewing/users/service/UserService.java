package com.nprogramming.interviewing.users.service;

import com.nprogramming.interviewing.users.domain.User;
import com.nprogramming.interviewing.users.domain.UserCreateForm;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(BigInteger id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);
}
