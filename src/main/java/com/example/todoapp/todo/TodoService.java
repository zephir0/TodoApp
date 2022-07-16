package com.example.todoapp.todo;

import com.example.todoapp.user.User;
import com.example.todoapp.user.UserRepository;
import com.example.todoapp.user.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserService userService;

    public TodoService(TodoRepository todoRepository,

                       UserService userService) {
        this.todoRepository = todoRepository;

        this.userService = userService;
    }


    public void addTodoToList(TodoDto todoDto) {
        Todo todo = new Todo();
        todo.setDescription(todoDto.getDescription());
        todo.setUser(userService.findUserId());
        showAllTodosDescriptions();
        todoRepository.save(todo);
    }

    public Collection<Todo> showAllTodosDescriptions() {
        User activeUser = userService.findUserId();
        return activeUser.getTodoCollections();
//        return todoCollections
//                .stream()
//                .map(Todo::getDescription)
//                .collect(Collectors.toList());
    }

}
