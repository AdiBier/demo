package com.example.demo.services;

import com.example.demo.models.Patient;
import com.example.demo.models.Treatment;
import com.example.demo.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {


    @Autowired
    private PatientRepository patientRepository;


    @Override
    public Page<Patient> getAllPatients(Pageable pageable) {
        return patientRepository.findAll(pageable);

    }

    @Override
    public List<Patient> getAllPatientsList() {
        return patientRepository.findAll();

    }

    @Override
    public Patient getPatient(Long id) {
        return patientRepository.getOne(id);
    }


    @Override
    public void savePatient(Patient patient) {
        patientRepository.save(patient);
    }

    @Override
    public Patient getById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id){ patientRepository.deleteById(id); }

    @Override
    public boolean exists(Long id){
        return patientRepository.existsById(id);
    }


}

