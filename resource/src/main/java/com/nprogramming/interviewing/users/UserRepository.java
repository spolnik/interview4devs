package com.nprogramming.interviewing.users;

import com.nprogramming.interviewing.users.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface UserRepository extends CrudRepository <User, BigInteger>{

    Optional<User> findOneByEmail(String email);
}
