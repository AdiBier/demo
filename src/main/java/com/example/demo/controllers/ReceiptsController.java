package com.example.demo.controllers;

import com.example.demo.models.Patient;
import com.example.demo.models.Receipt;
import com.example.demo.models.Treatment;
import com.example.demo.models.User;
import com.example.demo.services.PatientService;
import com.example.demo.services.ReceiptService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Secured({"ROLE_ADMIN", "ROLE_DENTIST"})
    @RequestMapping(value={"/receipts/add", "/receipts/edit"}, method= RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("receipt") Receipt receipt, BindingResult errors){

        if(errors.hasErrors()){
            return "receipts/rform";
        }
        receipt.setCreatedDate(new Date());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        receipt.setDentist(userService.getUserByUsername(userName));
        receiptService.save(receipt);
        return "redirect:/receipts";
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

    @InitBinder
    public void initBinder(WebDataBinder binder) {//Rejestrujemy edytory właściwości

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        CustomDateEditor dateEditor = new CustomDateEditor(dateFormat, false);
        binder.registerCustomEditor(Date.class, "createdDate", dateEditor);

        binder.setDisallowedFields("createdDate");//ze względu na bezpieczeństwo aplikacji to pole nie może zostać przesłane w formularzu

    }

}
