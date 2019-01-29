package com.example.demo.controllers;


import com.example.demo.models.Patient;
import com.example.demo.models.Treatment;
import com.example.demo.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes(names={})

public class PatientsListController {

    @Autowired
    private PatientService patientService;

    @RequestMapping(path = "/users/add")
    public String getAllPatients3(Model model){
        model.addAttribute("patient", patientService.getAllPatients2());
        return "users/pform";
    }

    @RequestMapping(value="/patients")
    public String showPatientList(Model model, Pageable pageable){
        model.addAttribute("patientListPage", patientService.getAllPatients(pageable));
        return "patients/plist";
    }
    // params = "id", method = RequestMethod.GET
//    @RequestMapping(path="/patients/details")
//    public String details(Model model, Long id){
//        //log.info("Pokazywanie szczegółów");
//        Patient patient = patientService.getPatient(id);
//        model.addAttribute("patient", patient);
//        return "patients/pdetails";
//    }

    @RequestMapping(value={"/patients/add", "/patients/edit"}, method= RequestMethod.GET)
    public String showForm(Model model, Optional<Long> id){
        Patient patient;
        if(id.isPresent()){
            model.addAttribute("action", "edit");
            patient = patientService.getById(id.get());
        } else {
            model.addAttribute("action", "add");
            patient = new Patient();
        }
        model.addAttribute("patient",patient);
        return "/patients/pform";
    }

//    @RequestMapping(value={"/patients/add2", "/patients/edit2"}, method= RequestMethod.GET)
//    public String showForm2(Model model, Optional<Long> id){
//        Patient patient;
//
//        if(id.isPresent()){
//            Long patientId = id.get();
//            model.addAttribute("action", "edit");
//            patient = patientService.getById(patientId);
//        } else {
//            model.addAttribute("action", "add");
//            patient = new Patient();
//        }
//        model.addAttribute("patient",patient);
//
//        return "/users/add";
//    }
    @Secured({"ROLE_ADMIN", "ROLE_DENTIST"})
    @RequestMapping(value={"/patients/add", "/patients/edit"}, method= RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("patient") Patient patient, BindingResult errors){

        if(errors.hasErrors()){
            return "patients/pform";
        }
        patientService.savePatient(patient);
        return "redirect:/patients";
    }


    @RequestMapping(value="/patients/delete")
    public String delete(Model model, Long id){

        if(patientService.exists(id)){
            patientService.delete(id);
        }
        return "redirect:/patients";
    }
    private String prepareQueryString(String queryString) {//np., did=20&page=2&size=20
        return queryString.substring(queryString.indexOf("&")+1);//obcinamy parametr did, bo inaczej po przekierowaniu znowu będzie wywołana metoda deleteVihicle
    }

    @RequestMapping(value="/patients/deactivate")
    public String deactivate(Model model, Long id){

        return "redirect:/patients";
    }



}

