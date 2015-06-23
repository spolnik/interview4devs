package com.nprogramming.interviewing.users.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

@RestController
public class LoginController {

    @RequestMapping("/user")
    public Map<String, Object> user(Principal user) {
        return Collections.<String, Object> singletonMap("name", user.getName());
    }
}
