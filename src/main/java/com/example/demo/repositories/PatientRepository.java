package com.example.demo.repositories;

import com.example.demo.models.Patient;
import com.example.demo.models.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
