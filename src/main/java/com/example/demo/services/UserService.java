package com.example.demo.services;

import com.example.demo.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    // Własne metody
    void save(User user);

    boolean isUniqueLogin(String login);
}
