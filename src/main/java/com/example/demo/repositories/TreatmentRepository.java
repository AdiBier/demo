package com.example.demo.repositories;

import com.example.demo.models.ScheduledTreatment;
import com.example.demo.models.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.JoinTable;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {

}
