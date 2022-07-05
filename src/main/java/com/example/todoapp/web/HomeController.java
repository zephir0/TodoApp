package com.example.todoapp.web;

import com.example.todoapp.message.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final MessageService messageService;

    public HomeController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String home(Model model) {
        String welcomeMessage = messageService.getWelcomeMessage();
        model.addAttribute("welcomeMessage", welcomeMessage);
        return "home";
    }
}
