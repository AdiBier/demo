package com.example.demo.services;

import com.example.demo.models.Patient;
import com.example.demo.models.Role;
import com.example.demo.models.Treatment;
import com.example.demo.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {

    Page<Role> getAllRoles(Pageable pageable);

    Role getRole(Long id);

    void saveRole(Role role);

    Role getById(Long id);

    void delete(Long id);

    boolean exists(Long id);



}
