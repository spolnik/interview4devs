package com.nprogramming.interviewing.candidates;

import com.nprogramming.interviewing.candidates.Candidate;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface CandidateRepository extends CrudRepository <Candidate, BigInteger>{
}
