package com.example.demo.services;

import com.example.demo.models.Report;
import com.example.demo.repositories.ReportRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    private ReportRepository reportRepository;

    @Override
    public Page<Report> getAllAdvertisements(Pageable pageable) {
        return reportRepository.findAll(pageable);
    }

    @Override
    public Page<Report> getAdvertisementsById(Long advertisement_id, Pageable pageable) {
        return null;
    }

    @Override
    public Report getAdvertisement(Long id) {
        return reportRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        reportRepository.deleteById(id);
    }

    @Override
    public void add(String title, String description) {

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
