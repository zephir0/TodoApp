package com.example.todoapp.web;

import com.example.todoapp.exceptions.RegistrationPasswordConfirmationException;
import com.example.todoapp.exceptions.UserAlreadyExistException;
import com.example.todoapp.registration.RegistrationData;
import com.example.todoapp.registration.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    final private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registrationData", new RegistrationData());
        return "registration";
    }

    @PostMapping("/register")
    public String userRegistration(final @Valid RegistrationData registrationData,
                                   final BindingResult bindingResult,
                                   final Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("registrationData", registrationData);
            return "registration";
        }
        try {
            registrationService.register(registrationData);
        } catch (UserAlreadyExistException e) {
            bindingResult.rejectValue("login", "registrationData.login", "An account already exists for this email.");
            model.addAttribute("registrationData", registrationData);
            return "registration";
        } catch (RegistrationPasswordConfirmationException e) {
            bindingResult.rejectValue("password", "registrationData.password", "Passwords are not the same");
            model.addAttribute("registrationData", registrationData);
            return "registration";
        }
        return "successfully-register.html";
    }





/*    @ModelAttribute(value = "registrationData")
    public RegistrationData registrationData() {
        return new RegistrationData();
    }*/
}
