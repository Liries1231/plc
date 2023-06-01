package com.example.CodeLC.controller;

import com.example.CodeLC.domain.Role;
import com.example.CodeLC.domain.User;
import com.example.CodeLC.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {


    @Autowired
    private UserRepos userRepos;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> map) {
        User user1 = userRepos.findByUsername(user.getUsername());
        if (user1 != null) {
            map.put("message", "user exists");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.AUTHOR));
        userRepos.save(user);

        return "redirect:/login";
    }


}
