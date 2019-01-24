package com.example.demo.controllers;


import com.example.demo.models.Patient;
import com.example.demo.models.Role;
import com.example.demo.models.Treatment;
import com.example.demo.services.PatientService;
import com.example.demo.services.RoleService;
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

public class RolesController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value="/roles", method = {RequestMethod.GET, RequestMethod.POST})
    public String showRoleList(Model model, Pageable pageable){
        model.addAttribute("roleListPage", roleService.getAllRoles(pageable));
        return "roles/rlist";


    }
    @Secured("IS_AUTHENTICATED_FULLY")
    @RequestMapping(value="/roles/rlist", params = "id", method = RequestMethod.GET)
    public String showRoleDetails(Model model, Long id){
        //log.info("Pokazywanie szczegółów");
        model.addAttribute("role", roleService.getRole(id));
        return "RoleDetails";
    }



   /* @RequestMapping(path = "/patients")
    public String index(Model model, Pageable pageable) {
        List<Patient> patients = new ArrayList<>();


        Page page =  new PageImpl<Patient>(patients, pageable, patients.size());

        model.addAttribute("patientsPage", page);

        return "patients/plist";
    }*/

    @RequestMapping(value={"/roles/add", "/roles/edit"}, method= RequestMethod.GET)
    public String showForm(Model model, Optional<Long> id){
        Role role;


        if(id.isPresent()){
            Long roleId = id.get();
            model.addAttribute("action", "edit");
            role = roleService.getById(roleId);
        } else {
            model.addAttribute("action", "add");
            role = new Role();

        }

        model.addAttribute("role",role);

        return "/roles/rform";
    }

    @RequestMapping(value={"/roles/add", "/roles/edit"}, method= RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("role") Role role, BindingResult errors){

//        if(errors.hasErrors()){
//            return "patients/form";
//        }
        roleService.saveRole(role);
        return "redirect:/roles";
    }


    @RequestMapping(value="/roles/delete")
    public String delete(Model model, Long id){

        if(roleService.exists(id)){
            roleService.delete(id);
        }
        return "redirect:/roles";
    }
    private String prepareQueryString(String queryString) {//np., did=20&page=2&size=20
        return queryString.substring(queryString.indexOf("&")+1);//obcinamy parametr did, bo inaczej po przekierowaniu znowu będzie wywołana metoda deleteVihicle
    }

    @RequestMapping(value="/roles/deactivate")
    public String deactivate(Model model, Long id){

        return "redirect:/roles";
    }

}

