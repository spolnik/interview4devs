package com.hiringdefined.candidates;

import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface CandidateRepository extends CrudRepository <Candidate, BigInteger> {
}
