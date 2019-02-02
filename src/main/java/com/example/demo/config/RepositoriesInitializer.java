package com.example.demo.config;

import com.example.demo.models.*;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

@Configuration
public class RepositoriesInitializer {

    @Autowired
    private TreatmentRepository treatmentRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ReportRepository reportRepository;

    @Bean
    InitializingBean init() {

        return () -> {

            if (roleRepository.findAll().isEmpty() == true) {
                try {
                    Role roleUser = new Role("ROLE_USER");
                    roleRepository.save(roleUser);
                    Role roleAdmin = new Role("ROLE_ADMIN");
                    roleRepository.save(roleAdmin);
                    Patient userPatient = new Patient(null, "First", "User", "85040744983");
                    patientRepository.save(userPatient);
                    Patient adminPatient = new Patient(null, "Second", "Admin", "63010648114");
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
                    admin.setRoles(new HashSet<>(Arrays.asList(roleUser, roleAdmin)));
                    admin.setPassword(passwordEncoder.encode("admin"));

                    Treatment treatment1 = new Treatment();
                    treatment1.setName("Wybielenie zębów");
                    treatment1.setPrice(new BigDecimal(350));
                    treatment1.setActive(true);
                    treatmentRepository.save(treatment1);

                    Treatment treatment2 = new Treatment();
                    treatment2.setName("Wyrwanie zęba");
                    treatment2.setPrice(new BigDecimal(50));
                    treatment2.setActive(true);
                    treatmentRepository.save(treatment2);

                    Treatment treatment3 = new Treatment();
                    treatment3.setName("Kanałowe leczenie");
                    treatment3.setPrice(new BigDecimal(750));
                    treatment3.setActive(true);
                    treatmentRepository.save(treatment3);

                    Treatment treatment4 = new Treatment();
                    treatment4.setName("Plomba");
                    treatment4.setPrice(new BigDecimal(75));
                    treatment4.setActive(true);
                    treatmentRepository.save(treatment4);

                    Treatment treatment5 = new Treatment();
                    treatment5.setName("Prześwietlenie zęba");
                    treatment5.setPrice(new BigDecimal(25));
                    treatment5.setActive(true);
                    treatmentRepository.save(treatment5);

                    Treatment treatment6 = new Treatment();
                    treatment6.setName("Piaskowanie zębów");
                    treatment6.setPrice(new BigDecimal(200));
                    treatment6.setActive(true);
                    treatmentRepository.save(treatment6);

                    userRepository.save(user);
                    userRepository.save(admin);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
    }

}
