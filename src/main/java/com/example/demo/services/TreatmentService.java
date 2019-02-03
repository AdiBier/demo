package com.example.demo.services;

import com.example.demo.controllers.commands.VehicleFilter;
import com.example.demo.models.Treatment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TreatmentService {
    Page<Treatment> getAllTreatments(Pageable pageable);

    Treatment save(Treatment treatment);

    Treatment getById(Long id);

    List<Treatment> getAllTreatmentsList();

    void delete(Long id);

    boolean exists(Long id);

    Page<Treatment> getSearch(VehicleFilter search, Pageable pageable);

}
