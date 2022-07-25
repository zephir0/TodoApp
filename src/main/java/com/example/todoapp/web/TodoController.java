package com.example.todoapp.web;

import com.example.todoapp.message.MessageService;
import com.example.todoapp.todo.TodoDto;
import com.example.todoapp.todo.TodoRepository;
import com.example.todoapp.todo.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService,
                          MessageService messageService,
                          TodoRepository repository) {
        this.todoService = todoService;
        this.messageService = messageService;
        this.todoRepository = repository;
    }

    private final MessageService messageService;


    private final TodoRepository todoRepository;


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
            todoService.addTaskToList(todoDto);
        } catch (RuntimeException e) {
            model.addAttribute("todoDto", todoDto);
            return "home";
        }

        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id) {
        todoRepository.deleteById(id);
        return "redirect:/";
    }


}
