package com.nprogramming.interviewing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CandidateController {

    private final CandidatesRepository repository;

    @Autowired
    public CandidateController(CandidatesRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("candidates", repository.findAll());
        return "index";
    }
}
