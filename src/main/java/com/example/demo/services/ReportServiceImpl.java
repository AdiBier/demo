package com.example.demo.services;

import com.example.demo.models.Patient;
import com.example.demo.models.Report;
import com.example.demo.repositories.ReportRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    private ReportRepository reportRepository;

    @Override
    public Page<Report> getallReports(Pageable pageable) {
        return reportRepository.findAll(pageable);
    }

    @Override
    public Page<Report> getReportsById(Long advertisement_id, Pageable pageable) {
        return null;
    }

    @Override
    public Report getReport(Long id) {
        return reportRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        reportRepository.deleteById(id);
    }

    @Override
    public void add(Patient patient, String title, String description) {
    }

    @Override
    public Report save(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public boolean exist(Long id) {
        return reportRepository.existsById(id);
    }
}
