package com.example.demo.services;

import com.example.demo.models.Patient;
import com.example.demo.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void save(User user);

    Page<User> getAllUsers(Pageable pageable);

    User getUser(Long id);

    User getRole(Long id);

    void saveUser(User user);

    User getById(Long id);

    User getUserByUsername(String username);

    void delete(Long id);

    boolean exists(Long id);

    boolean isUniqueLogin(String login);
}
