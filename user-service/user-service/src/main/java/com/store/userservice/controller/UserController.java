package com.store.userservice.controller;


import com.store.userservice.model.User;
import com.store.userservice.service.UserProducer;
import com.store.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserProducer userProducer;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    // test
    @GetMapping("/send")
    public String sendMessage(@RequestParam("message") String message) {
        userProducer.sendMessage(message);
        System.out.println("Done");
        return "Message sent: " + message;
    }
}
