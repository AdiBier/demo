package com.example.demo.controllers;

import com.example.demo.models.Patient;
import com.example.demo.models.Report;
import com.example.demo.services.PatientService;
import com.example.demo.services.ReportService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
public class ReportsController {

    @Autowired
    private ReportService reportService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/reports")
    public String index(Model model, org.springframework.data.domain.Pageable pageable){
        Page<Report> report  = reportService.getallReports(pageable);
        model.addAttribute("reportsPage", report);
        return "reports/rlist";
    }

    @RequestMapping(path = "/reports/details")
    public String details(Model model, Long id) {
        Report report = reportService.getReport(id);
        model.addAttribute("report", report);
        return "reports/rdetails";
    }

    @RequestMapping(value={"/reports/form"}, method= RequestMethod.GET)
    public String showForm(Model model, Optional<Long> id){
        Report report = id.isPresent() ?
                reportService.getReport(id.get()) :
                new Report();
        model.addAttribute("report", report);
        return "reports/rform";
    }

    @RequestMapping(value={"/reports/form"}, method= RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("report") Report report, BindingResult errors){
        if(report.getCreatedDate() == null)
        {
            report.setCreatedDate(new Date());
        }
        if(report.getUser().getId() == null){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            report.setUser(userService.getUserByUsername(userName));
        }
        reportService.save(report);
        return "redirect:/reports";
    }

    @ModelAttribute("patientsList")
    public List<Patient> loadPatients(){
        List<Patient> patients = patientService.getAllPatientsList();
        return patients;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/reports/delete")
    public String delete(Model model, Long id){

        if(reportService.exist(id)){
            reportService.delete(id);
        }
        return "redirect:/reports";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
