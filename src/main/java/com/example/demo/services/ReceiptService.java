package com.example.demo.services;

import com.example.demo.models.Patient;
import com.example.demo.models.Receipt;
import com.example.demo.models.User;
import com.example.demo.models.Visit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface ReceiptService {

    Page<Receipt> getAllReceipts(Pageable pageable);

    Page<Receipt> getReceiptsByPatientId(Long patient_id, Pageable pageable);

    Receipt getReceipt(Long id);

    void delete(Long id);

    Receipt save(Receipt receipt);

    boolean exists(Long id);
}
