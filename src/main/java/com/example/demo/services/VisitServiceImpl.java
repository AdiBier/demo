package com.example.demo.services;

import com.example.demo.models.Patient;
import com.example.demo.models.User;
import com.example.demo.models.Visit;
import com.example.demo.repositories.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    private VisitRepository visitRepository;


    @Override
    public Page<Visit> getAllVisits(Pageable pageable) {
        return visitRepository.findAll(pageable);
    }

    @Override
    public Page<Visit> getVisitsByPatientId(Long patient_id,Pageable pageable) {
        return visitRepository.findVisitsByPatient_Id(patient_id, pageable);
    }

    @Override
    public Visit getVisit(Long id) {
        return visitRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        visitRepository.delete(id);
    }

    @Override
    public void add(Date visitDate, Patient patient, User dentist) {

    }

    @Override
    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }
}
