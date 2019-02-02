package com.example.demo.services;

import com.example.demo.models.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReportService {

    Page<Report> getAllAdvertisements(Pageable pageable);

    Page<Report> getAdvertisementsById(Long advertisement_id, Pageable pageable);

    Report getAdvertisement(Long id);

    void delete(Long id);

    void add(String title, String description);

    Report save(Report report);

    boolean exist(Long id);
}
