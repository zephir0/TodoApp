package com.todoapp.web;

import com.todoapp.message.MessageService;
import com.todoapp.todo.Todo;
import com.todoapp.todo.TodoDto;
import com.todoapp.todo.TodoRepository;
import com.todoapp.todo.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        return "redirect:/add";
    }


    @PostMapping("/add")
    String addTodoTask(final TodoDto todoDto,
                       final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/add";
        }
        try {
            todoService.addTaskToList(todoDto);
        } catch (RuntimeException e) {
            return "redirect:/add";
        }

        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id) {
        todoRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/edit/{id}")
    public String editTodoItem(@PathVariable("id") Long id, TodoDto todoDto) {
        if (todoRepository.findById(id).isPresent()) {
            Todo todo = todoRepository.findById(id).get();
            System.out.println("To jest todoDto: " + todoDto.getDescription());
            todo.setDescription(todoDto.getDescription());
            todoRepository.save(todo);
        }
        return "redirect:/";
    }


}
