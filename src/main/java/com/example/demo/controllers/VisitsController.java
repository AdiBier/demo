package com.example.demo.controllers;


import com.example.demo.models.*;
import com.example.demo.services.PatientService;
import com.example.demo.services.TreatmentService;
import com.example.demo.services.VisitService;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

@Controller
public class VisitsController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private VisitService visitService;

    @Autowired
    private TreatmentService treatmentService;

    @RequestMapping(path = "/visits/patient")
    public String patientList(Model model, Pageable pageable, Long patient_id) {

        model.addAttribute("visitsPage", visitService.getVisitsByPatientId(patient_id, pageable));
        model.addAttribute("patient", patientService.getById(patient_id));

        return "visits/patient_list";
    }

    @RequestMapping(path = "/visit/list")
    public String index(Model model, org.springframework.data.domain.Pageable pageable){
        model.addAttribute("Visit", visitService.getAllVisits(pageable));

        return "registerVisit/list";
    }

    @RequestMapping(path = "/visit/details")
    public String details(Model model, Long id){
        Visit visit = visitService.getVisit(id);
        model.addAttribute("visit", visit);

        return "visit/details";
    }
    //edit/add
    @RequestMapping(value ={ "/visits/add", "/visits/edit"},method = RequestMethod.GET)
    public String showForm(Model model, Optional<Long>id){
        Visit visit;
        if(id.isPresent()){
            Long visitId = id.get();
            model.addAttribute("action","edit");
            visit = visitService.getVisit(visitId);
        }else{
            model.addAttribute("action", "add");
            visit = new Visit();
        }
        model.addAttribute("visit",visit);

        return "visits/form";
    }
    @RequestMapping(value={"/visits/add", "/visits/edit"}, method= RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("visits") Visit visit, BindingResult error){

//        if(error.hasErrors()){
//            return "visits/form";
//        }
        Long id = visit.getId();
        if(id != null){
            Visit oldVisit = visitService.getVisit(id);
            //TODO
            // if (!oldVisit)
            // porownaj z terminem wizyty i sprawdz czy nie jest zajeta
        }
        return "redirect:/visits";
    }



    @RequestMapping(path = "/visits/delete")
    public String delete(Model model, Long id){

        if(treatmentService.exists(id)){
            if(treatmentService.isAssignedToAnyScheduledTreatment(id)){
                model.addAttribute("visit",visitService.getVisit(id));
                return "/visits/can_not_delete";
            }
            visitService.delete(id);
        }
        return "redirect:/visits";
    }

}

