package com.example.demo.services;

import com.example.demo.models.ScheduledTreatment;
import com.example.demo.repositories.ScheduledTreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduledTreatmentServiceImpl implements ScheduledTreatmentService {

    @Autowired
    private ScheduledTreatmentRepository scheduledTreatmentRepository;

    @Override
    public ScheduledTreatment getById(Long id) {
        return scheduledTreatmentRepository.findById(id).orElse(null);
    }

    @Override
    public ScheduledTreatment save(ScheduledTreatment scheduledTreatment) {
        return scheduledTreatmentRepository.save(scheduledTreatment);
    }
}
