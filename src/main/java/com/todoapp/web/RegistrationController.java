package com.todoapp.web;

import com.todoapp.exceptions.RegPassConfirmationException;
import com.todoapp.exceptions.UserAlreadyExistException;
import com.todoapp.registration.RegistrationData;
import com.todoapp.registration.RegistrationService;
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
                                   final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/register";
        }
        try {
            registrationService.register(registrationData);
        } catch (UserAlreadyExistException e) {
            bindingResult.rejectValue("login", "registrationData.login", "An account already exists for this email.");
            return "redirect:/register";
        } catch (RegPassConfirmationException e) {
            bindingResult.rejectValue("password", "registrationData.password", "Passwords are not the same");
            return "redirect:/register";
        }
        return "successfully-register.html";
    }


}
