package com.example.demo.services;

import com.example.demo.models.Patient;
import com.example.demo.models.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReportService {

    Page<Report> getallReports(Pageable pageable);

    Page<Report> getReportsById(Long advertisement_id, Pageable pageable);

    Report getReport(Long id);

    void delete(Long id);

    void add(Patient patient, String title, String description);

    Report save(Report report);

    boolean exist(Long id);
}
