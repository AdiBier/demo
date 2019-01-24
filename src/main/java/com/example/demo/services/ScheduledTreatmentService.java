package com.example.demo.services;

import com.example.demo.models.ScheduledTreatment;

import java.util.Optional;

public interface ScheduledTreatmentService {

    ScheduledTreatment getById(Long id);

    ScheduledTreatment save(ScheduledTreatment scheduledTreatment);
}