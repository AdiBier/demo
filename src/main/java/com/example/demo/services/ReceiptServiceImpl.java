package com.example.demo.services;

import com.example.demo.models.Patient;
import com.example.demo.models.Receipt;
import com.example.demo.models.User;
import com.example.demo.models.Visit;
import com.example.demo.repositories.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReceiptServiceImpl implements ReceiptService{

    @Autowired
    private ReceiptRepository receiptRepository;


    @Override
    public Page<Receipt> getAllReceipts(Pageable pageable) {
        return receiptRepository.findAll(pageable);
    }

    @Override
    public Page<Receipt> getReceiptsByPatientId(Long patient_id, Pageable pageable) {
        return null;
    }

    @Override
    public Receipt getReceipt(Long id) {
        return receiptRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        receiptRepository.deleteById(id);
    }

    @Override
    public void add(Patient patient, User dentist, String description) {
    }

    @Override
    public boolean exists(Long id){
        return receiptRepository.existsById(id);
    }

    @Override
    public Receipt save(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

}
