package com.example.todoapp.web;

import com.example.todoapp.registration.RegistrationData;
import com.example.todoapp.registration.RegistrationService;
import com.example.todoapp.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    private RegistrationService registrationService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registrationData", new RegistrationData());
        return "registration";
    }

    @PostMapping("/register")
    public String userRegistration(final @Valid RegistrationData registrationData, final BindingResult bindingResult, final Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("registrationData", registrationData);
            return "registration";
        }
        try {
            registrationService.register(registrationData);
        } catch (RuntimeException e) {
            bindingResult.rejectValue("login", "registrationData.login", "An account already exists for this email.");
            model.addAttribute("registrationData", registrationData);
            return "registration";
        }
        return "home";
    }





/*    @ModelAttribute(value = "registrationData")
    public RegistrationData registrationData() {
        return new RegistrationData();
    }*/
}
