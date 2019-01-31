package com.example.demo.controllers;

import com.example.demo.models.Advertisement;
import com.example.demo.models.Receipt;
import com.example.demo.services.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Optional;


@Controller
public class AdvertisementsController {

    @Autowired
    private AdvertisementService advertisementService;

    @RequestMapping(path = "/advertisements")
    public String index(Model model, Pageable pageable){
        Page<Advertisement> advertisement  = advertisementService.getAllAdvertisements(pageable);
        model.addAttribute("advertisementsPage", advertisement);
        return "advertisements/alist";
    }

    @RequestMapping(path = "/advertisements/details")
    public String details(Model model, Long id) {
        Advertisement advertisement = advertisementService.getAdvertisement(id);
        model.addAttribute("advertisement",advertisement);
        return "advertisement/adetails";
    }

    @RequestMapping(value={"/advertisements/add", "/advertisements/edit"}, method= RequestMethod.GET)
    public String showForm(Model model, Optional<Long> id){
        Advertisement advertisement;
        if(id.isPresent()){
            model.addAttribute("action", "edit");
            advertisement = advertisementService.getAdvertisement(id.get());
        } else {
            model.addAttribute("action", "add");
            advertisement = new Advertisement();
        }
        model.addAttribute("advertisement",advertisement);
        return "advertisements/aform";
    }

    @Secured({"ROLE_ADMIN", "ROLE_DENTIST"})
    @RequestMapping(value={"/advertisements/add", "/advertisements/edit"}, method= RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("advertisement") Advertisement advertisement, BindingResult errors){

        if(errors.hasErrors()){
            return "advertisements/rform";
        }
        //TODO wciaż null-a daje -_-
        advertisementService.save(advertisement);
        return "redirect:/advertisements";
    }

    @RequestMapping(value="/advertisements/delete")
    public String delete(Model model, Long id){

        if(advertisementService.exist(id)){
            advertisementService.delete(id);
        }
        return "redirect:/advertisements";
    }

}
