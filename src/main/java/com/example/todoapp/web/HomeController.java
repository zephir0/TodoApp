package com.example.todoapp.web;

import com.example.todoapp.message.MessageService;
import com.example.todoapp.todo.Todo;
import com.example.todoapp.todo.TodoDto;
import com.example.todoapp.todo.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class HomeController {
    private final MessageService messageService;
    private final TodoService todoService;
    public HomeController(MessageService messageService,
                          TodoService todoService) {
        this.messageService = messageService;
        this.todoService = todoService;
    }

    @GetMapping("/")
    public String home(Model model) {
        String welcomeMessage = messageService.getWelcomeMessage();
        Collection<Todo> todos = todoService.showAllTasks();

        model.addAttribute("welcomeMessage", welcomeMessage);
        model.addAttribute("todoDto", new TodoDto());
        model.addAttribute("todoList", todos);

        return "home";
    }



}
