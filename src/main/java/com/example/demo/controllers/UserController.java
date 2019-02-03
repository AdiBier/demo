package com.example.demo.controllers;

import com.example.demo.models.Patient;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.services.PatientService;
import com.example.demo.services.RoleService;
import com.example.demo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@SessionAttributes(names = {})

public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private PatientService patientService;

    @RequestMapping(value = "/users", method = {RequestMethod.GET, RequestMethod.POST})
    public String showUserList(Model model, Pageable pageable) {
        model.addAttribute("userListPage", userService.getAllUsers(pageable));
        return "users/ulist";


    }

    @RequestMapping(path = "/users/details")
    public String details(Model model, Long id) {
        User user = userService.getUser(id);
        model.addAttribute("user",user);
        return "users/udetails";
    }

    @Secured("IS_AUTHENTICATED_FULLY")
    @RequestMapping(value = "/users/ulist", params = "id", method = RequestMethod.GET)
    public String showUserDetails(Model model, Long id) {
        //log.info("Pokazywanie szczegółów");
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("role", userService.getRole(id));
        return "UserDetails";
    }


    @RequestMapping(value = {"/users/add", "/users/edit", "/register"}, method = RequestMethod.GET)
    public String showForm(Model model, Optional<Long> id) {
        User user;
        Patient patient;
        if (id.isPresent()) {
            model.addAttribute("action", "edit");
            user = userService.getById(id.get());
            patient = user.getPatient();
        } else {
            model.addAttribute("action", "add");
            user = new User();
            patient = new Patient();
        }

        model.addAttribute("user", user);
        model.addAttribute("patient", patient);

        return "users/uform";
    }

    @RequestMapping(value = {"/users/add","/users/edit", "/register"}, method = RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("user") User user, @Valid @ModelAttribute("patient") Patient patient, BindingResult errors) {
        if (errors.hasErrors()) {
            return "users/uform";
        }
        patientService.savePatient(patient);
        user.setPatient(patient);
        userService.saveUser(user);
        return "redirect:/";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/users/delete")
    public String delete(Long id) {

        if (userService.exists(id)) {
            userService.delete(id);
        }
        return "redirect:/users";
    }

    private String prepareQueryString(String queryString) {//np., did=20&page=2&size=20
        return queryString.substring(queryString.indexOf("&") + 1);//obcinamy parametr did, bo inaczej po przekierowaniu znowu będzie wywołana metoda deleteVihicle
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/users/active")
    public String toggleActive(Long id) {
        User user = userService.getById(id);
        user.setEnabled(!user.isEnabled());
        userService.saveUser(user);
        return "redirect:/users";
    }

}