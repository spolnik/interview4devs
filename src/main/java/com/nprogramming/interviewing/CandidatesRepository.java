package com.nprogramming.interviewing;

import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface CandidatesRepository extends CrudRepository <Candidate, BigInteger>{
}
