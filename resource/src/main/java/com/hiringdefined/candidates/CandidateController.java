package com.hiringdefined.candidates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateRepository repository;

    @Autowired
    public CandidateController(CandidateRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Candidate> getAll() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Candidate add(@RequestBody Candidate item) {
        return repository.save(item);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Candidate update(@RequestBody Candidate updatedItem, @PathVariable BigInteger id) {
        updatedItem.setId(id);
        return repository.save(updatedItem);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable BigInteger id) {
        repository.delete(id);
    }
}
