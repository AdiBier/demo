package com.example.demo.controllers;

import com.example.demo.models.Patient;
import com.example.demo.models.Receipt;
import com.example.demo.services.PatientService;
import com.example.demo.services.ReceiptService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.propertyeditors.CustomDateEditor;

import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ReceiptsController {

    @Autowired
    private ReceiptService receiptService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private UserService userService;


    @RequestMapping(path = "/receipts")
    public String index(Model model, Pageable pageable) {
        model.addAttribute("receiptsPage", receiptService.getAllReceipts(pageable));
        return "receipts/rlist";
    }


    @RequestMapping(path = "/receipts/details")
    public String details(Model model, Long id) {
        Receipt receipt = receiptService.getReceipt(id);
        model.addAttribute("receipt",receipt);
        return "receipts/rdetails";
    }


    @RequestMapping(value={"/receipts/form"}, method= RequestMethod.GET)
    public String showForm(Model model, Optional<Long> id){
        Receipt receipt = id.isPresent() ?
                receiptService.getReceipt(id.get()) :
                new Receipt();
        model.addAttribute("receipt", receipt);
        return "receipts/rform";
    }


    @RequestMapping(value={"/receipts/form"}, method= RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("receipt") Receipt receipt, BindingResult errors){
        if(receipt.getCreatedDate() == null)
        {
            receipt.setCreatedDate(new Date());
        }
        if(receipt.getDentist().getId() == null){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            receipt.setDentist(userService.getUserByUsername(userName));
        }
        receiptService.save(receipt);
        return "redirect:/receipts";
    }

    @ModelAttribute("patientsList")
    public List<Patient> loadPatients(){
        List<Patient> patients = patientService.getAllPatientsList();
        return patients;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value="/receipts/delete")
    public String delete(Model model, Long id){

        if(receiptService.exists(id)){
            receiptService.delete(id);
        }
        return "redirect:/receipts";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
