package com.example.demo.services;

import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    //bez adnotacji @Transactional sesja jest zamykana po wywołaniu findByUsername, co uniemożliwia dociągnięcie ról, gdyż fetch=EAGER.
    //ponadto, musi być włączone zarządzanie transakcjami @EnableTransactionManagement
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.demo.models.User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return createUserDetails(user);
    }

    private UserDetails createUserDetails(com.example.demo.models.User user) {
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        for (Role role : user.getRoles()){
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getType().toString()));
//        }

        Set<GrantedAuthority> grantedAuthorities =
                user.getRoles().stream().map(//mapowanie Role na GrantedAuthority
                        r -> new SimpleGrantedAuthority(r.getType().toString())
                ).collect(Collectors.toSet());

        return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, grantedAuthorities);
    }

    @Override
    public boolean isUniqueLogin(String username) {
        return userRepository.findByUsername(username) == null;
    }

    @Override
    public List<com.example.demo.models.User> getAllUsersList() {
        return userRepository.findAll();
    }

    @Override
    public Page<com.example.demo.models.User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);

    }

    @Override
    public com.example.demo.models.User getUser(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public com.example.demo.models.User getRole(Long id) {
        return null;
    }

    @Override
    public void saveUser(com.example.demo.models.User user) {
        Role userRole = roleRepository.findRoleByType("ROLE_USER");
        List roles = Arrays.asList(userRole);
        user.setRoles(new HashSet<>(roles));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPasswordConfirm(null);
        userRepository.saveAndFlush(user);
    }

    @Override
    public com.example.demo.models.User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public com.example.demo.models.User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void delete(Long id){ userRepository.deleteById(id); }

    @Override
    public boolean exists(Long id){
        return userRepository.existsById(id);
    }



}

