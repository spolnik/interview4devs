package com.hiringdefined;

import com.hiringdefined.users.UserRepository;
import com.hiringdefined.domain.Role;
import com.hiringdefined.domain.User;
import com.hiringdefined.users.domain.UserCreateForm;
import com.hiringdefined.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Profile("!production")
public class DatabaseLoader {

    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public DatabaseLoader(
            UserRepository userRepository,
            UserService userService
    ) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostConstruct
    private void initDatabase() {

        userRepository.deleteAll();

        User user = userService.create(
                new UserCreateForm("test@email.com", "test", "test", Role.ADMIN)
        );

        userRepository.save(user);
    }
}
