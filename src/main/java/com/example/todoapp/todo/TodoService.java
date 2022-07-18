package com.example.todoapp.todo;

import com.example.todoapp.user.User;
import com.example.todoapp.user.UserService;
import org.springframework.stereotype.Service;

import java.util.Collection;

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
        todo.setUser(userService.findUser());
        showAllTodosDescriptions();
        todoRepository.save(todo);
    }

    public Collection<Todo> showAllTodosDescriptions() {
        User activeUser = userService.findUser();
        return activeUser.getTodoCollections();
//        return todoCollections
//                .stream()
//                .map(Todo::getDescription)
//                .collect(Collectors.toList());
    }

}
