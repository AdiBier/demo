package com.example.demo.controllers;


import com.example.demo.models.*;
import com.example.demo.services.PatientService;
import com.example.demo.services.TreatmentService;
import com.example.demo.services.UserService;
import com.example.demo.services.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class VisitsController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private VisitService visitService;

    @Autowired
    private UserService userService;

    @Autowired
    private TreatmentService treatmentService;

    @ModelAttribute("patientsList")
    public List<Patient> loadPatients(){
        List<Patient> patients = patientService.getAllPatientsList();
        return patients;
    }

    @ModelAttribute("treatmentsList")
    public List<Treatment> loadTreatments(){
        List<Treatment> treatments = treatmentService.getAllTreatmentsList();
        return treatments;
    }

    @ModelAttribute("dentistsList")
    public List<User> loadDentists(){
        List<User> dentists = userService.getAllUsersList();
        return dentists;
    }


    @RequestMapping(path = "/visits")
    public String index(Model model, org.springframework.data.domain.Pageable pageable){
        model.addAttribute("visitsPage", visitService.getAllVisits(pageable));

        return "visits/vlist";
    }


    @RequestMapping(path = "/visits/details")
    public String details(Model model, Long id){
        Visit visit = visitService.getVisit(id);
        model.addAttribute("visit", visit);

        return "visits/vdetails";
    }


    @RequestMapping(value ={ "/visits/form"},method = RequestMethod.GET)
    public String showForm(Model model, Optional<Long>id){
        Visit visit;
        if(id.isPresent()){
            visit = visitService.getVisit(id.get());
        }else{
            visit = new Visit();
        }
        model.addAttribute("visit",visit);
        return "visits/vform";
    }


    @RequestMapping(value={"/visits/form"}, method= RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("visit") Visit visit, BindingResult error){
/*
        if(error.hasErrors()){
            error
                    .getFieldErrors()
                    .stream()
                    .forEach(f -> System.out.println(f.getField() + ": " + f.getDefaultMessage()));
            return "visits/vform";
       }
       */
       visit.setTotal(visitService.getTotal(visit));
       visitService.save(visit);
       return "redirect:/visits";
    }

    @RequestMapping(value = "/visits/payment")
    public String togglePayment(Long id) {
        Visit visit = visitService.getVisit(id);
        visit.setPaid(!visit.isPaid());
        visitService.save(visit);
        return "redirect:/visits";
    }


    @RequestMapping(path = "/visits/delete")
    public String delete(Model model, Long id){

        if(visitService.exists(id)){
            visitService.delete(id);
        }
        return "redirect:/visits";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}

