package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    User findByEmail(String email);
    void update(User user);
    void save(User user);
}
