package com.example.todoapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    LoginService loginService;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }



}
