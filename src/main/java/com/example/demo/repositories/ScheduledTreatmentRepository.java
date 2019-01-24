package com.example.demo.repositories;

import com.example.demo.models.Role;
import com.example.demo.models.ScheduledTreatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledTreatmentRepository extends JpaRepository<ScheduledTreatment, Long> {

    ScheduledTreatment findScheduledTreatmentByTreatment_Id(Long treatment_id);
}
