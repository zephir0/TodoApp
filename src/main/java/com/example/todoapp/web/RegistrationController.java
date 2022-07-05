package com.example.todoapp.web;

import com.example.todoapp.registration.RegistrationData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationController {
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registrationData", new RegistrationData());
        return "registration";
    }
}
