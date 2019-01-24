package com.example.demo.repositories;

import com.example.demo.models.Visit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    //TODO someone
  //  Visit findById(Long id);
    Visit getVisitById(Long id);

    Page<Visit> findVisitsByPatient_Id(Long patient_id, Pageable pageable);

    void delete(Long id);

//InfoStuff
//                findOne(…)          ║ findById(…)           ║
//            ╠═════════════════════╬═══════════════════════╣
//            ║ save(Iterable)      ║ saveAll(Iterable)     ║
//            ╠═════════════════════╬═══════════════════════╣
//            ║ findAll(Iterable)   ║ findAllById(…)        ║
//            ╠═════════════════════╬═══════════════════════╣
//            ║ delete(ID)          ║ deleteById(ID)        ║
//            ╠═════════════════════╬═══════════════════════╣
//            ║ delete(Iterable)    ║ deleteAll(Iterable)   ║
//            ╠═════════════════════╬═══════════════════════╣
//            ║ exists()            ║ existsById(…)

}
