package com.store.userservice.controller;


import com.store.userservice.model.User;
import com.store.userservice.service.UserProducer;
import com.store.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserProducer userProducer;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);

        // send to kafka
        userProducer.sendUserCreatedEvent(String.valueOf(registeredUser));

        return registeredUser;
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();

    }

    @PostMapping("/login")
    public Map<String, String> loginUser(@RequestBody User user) {
        String token = userService.loginUser(user.getUsername(), user.getPassword());
        return Map.of("token", token); // Повертаємо токен у форматі JSON
    }

}
