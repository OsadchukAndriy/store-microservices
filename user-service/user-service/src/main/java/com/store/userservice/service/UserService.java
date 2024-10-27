package com.store.userservice.service;


import com.store.userservice.model.User;

import java.util.List;


public interface UserService {

    public User registerUser(User user);

    public User findByUsername(String username);

    public List<User> findAllUsers();
}
