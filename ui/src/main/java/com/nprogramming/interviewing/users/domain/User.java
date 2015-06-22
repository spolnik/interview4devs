package com.nprogramming.interviewing.users.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigInteger;

@Document
public class User implements Serializable {

    @Id
    private BigInteger id;

    private String email;

    private String passwordHash;

    private Role role;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                "email='" + email.replaceFirst("@.+", "@***") + '\'' +
                ", passwordHash=***'" + passwordHash.substring(0, 10) +
                ", role=" + role +
                '}';
    }
}
