package com.example.demo.controllers;

import com.example.demo.models.Report;
import com.example.demo.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Optional;


@Controller
public class ReportsController {

    @Autowired
    private ReportService reportService;

    @RequestMapping(path = "/advertisements")
    public String index(Model model, Pageable pageable){
        Page<Report> advertisement  = reportService.getAllAdvertisements(pageable);
        model.addAttribute("advertisementsPage", advertisement);
        return "rlist";
    }

    @RequestMapping(path = "/advertisements/details")
    public String details(Model model, Long id) {
        Report report = reportService.getAdvertisement(id);
        model.addAttribute("advertisement", report);
        return "report/adetails";
    }

    @RequestMapping(value={"/advertisements/add", "/advertisements/edit"}, method= RequestMethod.GET)
    public String showForm(Model model, Optional<Long> id){
        Report report;
        if(id.isPresent()){
            model.addAttribute("action", "edit");
            report = reportService.getAdvertisement(id.get());
        } else {
            model.addAttribute("action", "add");
            report = new Report();
        }
        model.addAttribute("advertisement", report);
        return "rform";
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value={"/advertisements/add", "/advertisements/edit"}, method= RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("advertisement") Report report, BindingResult errors){

        if(errors.hasErrors()){
            return "advertisements/rform";
        }
        //TODO wciaż null-a daje -_-
        reportService.save(report);
        return "redirect:/advertisements";
    }

    @RequestMapping(value="/advertisements/delete")
    public String delete(Model model, Long id){

        if(reportService.exist(id)){
            reportService.delete(id);
        }
        return "redirect:/advertisements";
    }

}
