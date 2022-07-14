package com.example.todoapp.web;

import com.example.todoapp.todo.Todo;
import com.example.todoapp.todo.TodoDto;
import com.example.todoapp.todo.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.List;

@Controller
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/add")
    String showTodos(Model model) {
        model.addAttribute("todoDto", new TodoDto());
        return "home";
    }


    @PostMapping("/add")
    String addTodoTask(final TodoDto todoDto,
                       final Model model,
                       final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("todoDto", todoDto);
            return "home";
        }
        try {
            todoService.addTodoToList(todoDto);
        } catch (RuntimeException e) {
            model.addAttribute("todoDto", todoDto);
            return "home";
        }
        return "home";
    }
}
