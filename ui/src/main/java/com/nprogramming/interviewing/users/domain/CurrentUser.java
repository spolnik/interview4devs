package com.nprogramming.interviewing.users.domain;

import java.math.BigInteger;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    public CurrentUser(User user) {
        super(
                user.getEmail(),
                user.getPasswordHash(),
                createAuthorityList(user.getRole().toString())
        );

        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public BigInteger getId() {
        return user.getId();
    }

    public Role getRole() {
        return user.getRole();
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "user=" + user +
                '}' + super.toString();
    }
}
