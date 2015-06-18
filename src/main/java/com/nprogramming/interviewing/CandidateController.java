package com.nprogramming.interviewing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidatesRepository repository;

    @Autowired
    public CandidateController(CandidatesRepository repository) {
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
    public Candidate update(@RequestBody Candidate updatedItem, @PathVariable Long id) {
        updatedItem.setId(id);
        return repository.save(updatedItem);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        repository.delete(id);
    }
}
