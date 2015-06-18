package com.nprogramming.interviewing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
@Profile("!production")
public class DatabaseLoader {

    private final CandidatesRepository repository;

    @Autowired
    public DatabaseLoader(CandidatesRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    private void initDatabase() {
        repository.deleteAll();

        repository.save(Arrays.asList(new Candidate[]{
                        new Candidate("Madzia", "Spolnik", "msp1@email.com"),
                        new Candidate("Julia", "Spolnik", "jsp1@email.com"),
                        new Candidate("Mikolaj", "Spolnik", "msp2@email.com"),
                        new Candidate("Jacek", "Spolnik", "jsp2@email.com"),
                })
        );
    }
}
