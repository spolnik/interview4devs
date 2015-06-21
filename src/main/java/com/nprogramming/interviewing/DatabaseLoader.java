package com.nprogramming.interviewing;

import com.nprogramming.interviewing.candidates.Candidate;
import com.nprogramming.interviewing.candidates.CandidateRepository;
import com.nprogramming.interviewing.users.UserRepository;
import com.nprogramming.interviewing.users.domain.Role;
import com.nprogramming.interviewing.users.domain.User;
import com.nprogramming.interviewing.users.domain.UserCreateForm;
import com.nprogramming.interviewing.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
@Profile("!production")
public class DatabaseLoader {

    private final CandidateRepository candidateRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public DatabaseLoader(
            CandidateRepository candidateRepository,
            UserRepository userRepository,
            UserService userService
    ) {
        this.candidateRepository = candidateRepository;
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

        candidateRepository.deleteAll();

        candidateRepository.save(Arrays.asList(new Candidate[]{
                        new Candidate("Madzia", "Spolnik", "msp1@email.com"),
                        new Candidate("Julia", "Spolnik", "jsp1@email.com"),
                        new Candidate("Mikolaj", "Spolnik", "msp2@email.com"),
                        new Candidate("Jacek", "Spolnik", "jsp2@email.com"),
                })
        );
    }
}
