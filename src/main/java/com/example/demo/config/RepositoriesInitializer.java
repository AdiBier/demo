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
    private ScheduledTreatmentRepository scheduledTreatmentRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Bean
    InitializingBean init() {

        return () -> {

            if (roleRepository.findAll().isEmpty() == true) {
                try {
                    Role roleUser = new Role("ROLE_USER");
                    roleRepository.save(roleUser);
                    Role roleAdmin = new Role("ROLE_ADMIN");
                    roleRepository.save(roleAdmin);
                    Role roleDentist = new Role("ROLE_DENTIST");
                    roleRepository.save(roleDentist);
                    Patient userPatient = new Patient(null, "First", "User");
                    patientRepository.save(userPatient);
                    Patient adminPatient = new Patient(null, "Second", "Admin");
                    patientRepository.save(adminPatient);
                    Patient dentistPatient = new Patient(null, "Third", "Dentist");
                    patientRepository.save(dentistPatient);

                    User user = new User();
                    user.setUsername("user");
                    user.setEnabled(true);
                    user.setPatient(userPatient);
                    user.setRoles(new HashSet<>(Arrays.asList(roleUser)));
                    user.setPassword(passwordEncoder.encode("user"));

                    User dentist = new User();
                    dentist.setUsername("dentist");
                    dentist.setEnabled(true);
                    dentist.setPatient(dentistPatient);
                    dentist.setRoles(new HashSet<>(Arrays.asList(roleDentist, roleUser)));
                    dentist.setPassword(passwordEncoder.encode("dentist"));

                    User admin = new User();
                    admin.setUsername("admin");
                    admin.setEnabled(true);
                    admin.setPatient(adminPatient);
                    admin.setRoles(new HashSet<>(Arrays.asList(roleDentist, roleUser, roleAdmin)));
                    admin.setPassword(passwordEncoder.encode("admin"));

                    Treatment treatment1 = new Treatment();
                    treatment1.setName("Wybielenie zębów");
                    treatment1.setPrice(new BigDecimal(1500));
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

                    Advertisement advertisement1 = new Advertisement();
                    advertisement1.setTitle("Pierwsze ogłoszenie testowe");
                    advertisement1.setDescription("Treść ogłoszenia testowe.");
                    advertisementRepository.save(advertisement1);

                    Advertisement advertisement2 = new Advertisement();
                    advertisement2.setTitle("WAZNE");
                    advertisement2.setDescription("W dniach 18/12/18 - 24/12/18 nie pracujemy.");
                    advertisementRepository.save(advertisement2);

                    Advertisement advertisement3 = new Advertisement();
                    advertisement3.setTitle("Ogloszenie informacyjne");
                    advertisement3.setDescription("Prosze przed przyjsciem umyc dokladnie zabki.");
                    advertisementRepository.save(advertisement3);

                    userRepository.save(user);
                    userRepository.save(admin);
                    userRepository.save(dentist);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
    }

}
