package com.example.demo.config;

import com.example.demo.models.Patient;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
public class RepositoriesInitializer {

    @Autowired
    private TreatmentRepository treatmentRepository;
    @Autowired
    private ScheduledTreatmentRepository scheduledTreatmentRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    InitializingBean init() {

        return () -> {

            if (roleRepository.findAll().isEmpty() == true) {
                try {
                    Role roleUser = new Role("ROLE_USER");
                    roleRepository.save(roleUser);
                    Role roleAdmin = new Role("ROLE_ADMIN");
                    roleRepository.save(roleAdmin);
                    Patient userPatient = new Patient(null, "First", "User");
                    patientRepository.save(userPatient);
                    Patient adminPatient = new Patient(null, "Second", "Admin");
                    patientRepository.save(adminPatient);

                    User user = new User();
                    user.setUsername("user");
                    user.setEnabled(true);
                    user.setPatient(userPatient);
                    user.setRoles(new HashSet<>(Arrays.asList(roleUser)));
                    user.setPassword(passwordEncoder.encode("user"));

                    User admin = new User();
                    admin.setUsername("admin");
                    admin.setEnabled(true);
                    admin.setPatient(adminPatient);
                    admin.setRoles(new HashSet<>(Arrays.asList(roleUser)));
                    admin.setPassword(passwordEncoder.encode("admin"));

                    userRepository.save(user);
                    userRepository.save(admin);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
    }

}
