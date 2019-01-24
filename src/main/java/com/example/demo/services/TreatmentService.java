package com.example.demo.services;

import com.example.demo.models.Treatment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface TreatmentService {
    Page<Treatment> getAllTreatments(Pageable pageable);

    Treatment save(Treatment treatment);

    Treatment getById(Long id);

    void delete(Long id);

    boolean exists(Long id);

    boolean isAssignedToAnyScheduledTreatment(Long id);

}
