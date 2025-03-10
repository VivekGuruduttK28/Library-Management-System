package com.project.library_management_system.contollers;

import org.springframework.web.bind.annotation.RestController;

import com.project.library_management_system.model.User;
import com.project.library_management_system.services.userService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class userControllers {
    
    @Autowired
    userService userservice;

    @PostMapping("/signup")
    public String signUpUser(@RequestBody User user) {
        // return "lol";
        return userservice.createUser(user);
    }
    
    @GetMapping("/users")
    public List<User> displayUsers() {
        return userservice.readUser();
    }
    
}
