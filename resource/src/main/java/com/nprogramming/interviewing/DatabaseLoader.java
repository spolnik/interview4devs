package com.nprogramming.interviewing;

import com.nprogramming.interviewing.candidates.Candidate;
import com.nprogramming.interviewing.candidates.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
@Profile("!production")
public class DatabaseLoader {

    private final CandidateRepository candidateRepository;

    @Autowired
    public DatabaseLoader(
            CandidateRepository candidateRepository
    ) {
        this.candidateRepository = candidateRepository;
    }

    @PostConstruct
    private void initDatabase() {

        candidateRepository.deleteAll();

        candidateRepository.save(Arrays.asList(new Candidate[]{
                        new Candidate("Madzia", "Spolnik", "msp1@email.com"),
                        new Candidate("Julia", "Spolnik", "jsp1@email.com"),
                        new Candidate("Mikolaj", "Spolnik", "msxp2@email.com"),
                        new Candidate("Jacek", "Spolnik", "jsp2@email.com"),
                })
        );
    }
}
