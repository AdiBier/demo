package com.example.demo.controllers;

import com.example.demo.models.Patient;
import com.example.demo.models.Receipt;
import com.example.demo.models.Treatment;
import com.example.demo.services.PatientService;
import com.example.demo.services.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
public class ReceiptsController {

    @Autowired
    private ReceiptService receiptService;
    @Autowired
    private PatientService patientService;

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

    @RequestMapping(value={"/receipts/add", "/receipts/edit"}, method= RequestMethod.GET)
    public String showForm(Model model, Optional<Long> id){
        Receipt receipt;
        if(id.isPresent()){
            model.addAttribute("action", "edit");
            receipt = receiptService.getReceipt(id.get());
        } else {
            model.addAttribute("action", "add");
            receipt = new Receipt();
        }
        model.addAttribute("receipt",receipt);
        return "receipts/rform";
    }

    @ModelAttribute("patientsList")
    public List<Patient> loadPatients(){
        List<Patient> patients = patientService.getAllPatients2();
        return patients;
    }

    @RequestMapping(value="/receipts/delete")
    public String delete(Model model, Long id){

        if(receiptService.exists(id)){
            receiptService.delete(id);
        }
        return "redirect:/receipts";
    }

}
