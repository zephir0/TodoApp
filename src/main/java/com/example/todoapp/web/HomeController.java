package com.example.todoapp.web;

import com.example.todoapp.message.MessageService;
import com.example.todoapp.todo.TodoDto;
import com.example.todoapp.todo.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
        model.addAttribute("todoDto", new TodoDto());
        return "home";
    }


}
