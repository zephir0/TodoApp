package com.todoapp.web;

import com.todoapp.message.MessageService;
import com.todoapp.todo.Todo;
import com.todoapp.todo.TodoDto;
import com.todoapp.todo.TodoService;
import com.todoapp.user.User;
import com.todoapp.user.UserRole;
import com.todoapp.user.UserRoles;
import com.todoapp.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class HomeController {
    private final MessageService messageService;
    private final TodoService todoService;



    public HomeController(MessageService messageService,
                          TodoService todoService) {
        this.messageService = messageService;
        this.todoService = todoService;

    }

    @GetMapping()
    public String home(@RequestParam(required = false, value = "sorting") String sorting,
                       Model model
                       ) {

        List<Todo> todos = todoService.sortingParam(sorting);
        String welcomeMessage = messageService.getWelcomeMessage();
        model.addAttribute("welcomeMessage", welcomeMessage);
        model.addAttribute("todoDto", new TodoDto());
        model.addAttribute("todoList", todos);
        return "home";
    }


}
