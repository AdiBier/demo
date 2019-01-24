package com.example.demo.services;

import com.example.demo.models.Patient;
import com.example.demo.models.Role;
import com.example.demo.models.Treatment;
import com.example.demo.repositories.PatientRepository;
import com.example.demo.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Page<Role> getAllRoles(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    //TODO
    @Override
    public Role getRole(Long id) {
        return roleRepository.findById(id).orElse(null);
    }


    @Override
    public void saveRole(Role role) { roleRepository.save(role);
    }
    //TODO SOMEONE
    @Override
    public Role getById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id){ roleRepository.deleteById(id);
    }

    @Override
    public boolean exists(Long id){
        return roleRepository.existsById(id);
    }


}
