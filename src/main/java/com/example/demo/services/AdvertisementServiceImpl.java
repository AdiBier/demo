package com.example.demo.services;

import com.example.demo.models.Advertisement;
import com.example.demo.repositories.AdvertisementRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private AdvertisementRepository advertisementRepository;

    @Override
    public Page<Advertisement> getAllAdvertisements(Pageable pageable) {
        return advertisementRepository.findAll(pageable);
    }

    @Override
    public Page<Advertisement> getAdvertisementsById(Long advertisement_id, Pageable pageable) {
        return null;
    }

    @Override
    public Advertisement getAdvertisement(Long id) {
        return advertisementRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        advertisementRepository.deleteById(id);
    }

    @Override
    public void add(String title, String description) {

    }

    @Override
    public Advertisement save(Advertisement advertisement) {
        return advertisementRepository.save(advertisement);
    }

    @Override
    public boolean exist(Long id) {
        return advertisementRepository.existsById(id);
    }
}
