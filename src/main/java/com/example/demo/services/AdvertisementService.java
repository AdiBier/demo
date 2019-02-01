package com.example.demo.services;

import com.example.demo.models.Advertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdvertisementService {

    Page<Advertisement> getAllAdvertisements(Pageable pageable);

    Page<Advertisement> getAdvertisementsById(Long advertisement_id, Pageable pageable);

    Advertisement getAdvertisement(Long id);

    void delete(Long id);

    void add(String title, String description);

    Advertisement save(Advertisement advertisement);

    boolean exist(Long id);
}
