package com.nprogramming.interviewing.candidates;

import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface CandidateRepository extends CrudRepository <Candidate, BigInteger> {
}
