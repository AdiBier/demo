package com.example.demo.services;

import com.example.demo.models.Patient;
import com.example.demo.models.Treatment;
import com.example.demo.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientService {

    Page<Patient> getAllPatients(Pageable pageable);

    List<Patient> getAllPatientsList();

    Patient getPatient(Long id);


    void savePatient(Patient patient);

    Patient getById(Long id);

    void delete(Long id);

    boolean exists(Long id);



}
