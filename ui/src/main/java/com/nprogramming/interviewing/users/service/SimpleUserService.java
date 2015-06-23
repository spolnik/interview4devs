package com.nprogramming.interviewing.users.service;

import com.nprogramming.interviewing.users.UserRepository;
import com.hiringdefined.domain.User;
import com.nprogramming.interviewing.users.domain.UserCreateForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

@Service
public class SimpleUserService implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleUserService.class);
    private final UserRepository userRepository;

    @Autowired
    public SimpleUserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(BigInteger id) {

        LOG.debug("Getting user={}", id);

        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {

        LOG.debug("Getting user by email={}", email.replaceFirst("@.*", "@***"));

        return userRepository.findOneByEmail(email);
    }

    @Override
    public Collection<User> getAllUsers() {

        LOG.debug("Getting all users");

        return asUserListSortedByEmails(
                userRepository.findAll()
        );
    }

    @Override
    public User create(UserCreateForm form) {

        User user = new User();

        user.setEmail(form.getEmail());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setRole(form.getRole());

        return userRepository.save(user);
    }

    private static List<User> asUserListSortedByEmails(final Iterable<User> iterable) {

        return stream(iterable.spliterator(), false)
                .sorted(
                        (u1, u2) -> u1.getEmail().compareTo(u2.getEmail())
                )
                .collect(Collectors.toList());
    }
}
