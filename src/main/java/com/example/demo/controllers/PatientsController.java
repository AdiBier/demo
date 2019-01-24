package com.example.demo.controllers;


import com.example.demo.models.Patient;
import com.example.demo.services.PatientService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@Controller
@SessionAttributes(names={})

public class PatientsController {

    private PatientService patientService;

    public PatientsController(PatientService patientService)
    {
        this.patientService = patientService;
    }

    //Controllery - funkcjonalnosc/logika
  /*  @Secured("ROLE_ADMIN")
    @RequestMapping(value="/patient/register", method= RequestMethod.GET)
    public String showForm(Model model, Optional<Long> id){

        model.addAttribute("Patient",
                id.isPresent()?
                        patientService.getPatient(id.get()):
                        new Patient());

        return "registerForm";
    }*/



    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/patient/register", method=RequestMethod.POST)
    //@ResponseStatus(HttpStatus.CREATED)
    public String processForm(@Valid @ModelAttribute("Patient") Patient v, BindingResult errors){

        if(errors.hasErrors()){
            return "registerForm";
        }

        //  log.info("Data utworzenia komponentu "+v.getCreatedDate());
        //  log.info("Data edycji komponentu "+new Date());

        patientService.savePatient(v);


        //do ukończenia!
        return "patients/list";//po udanym dodaniu/edycji przekierowujemy na listę
    }
    //TODO
    @RequestMapping(path = "/patient/register")
    public String register() {
        return "registerForm";
    }
}




