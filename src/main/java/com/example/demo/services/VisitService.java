package com.example.demo.services;

import com.example.demo.models.Patient;
import com.example.demo.models.User;
import com.example.demo.models.Visit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface VisitService {

    Page<Visit> getAllVisits(Pageable pageable);

    Page<Visit> getVisitsByPatientId(Long patient_id,Pageable pageable);

    Visit getVisit(Long id);

    void delete(Long id);

    void add(Date visiteDate, Patient patient, User dentist);

    Visit save(Visit visit);
}
