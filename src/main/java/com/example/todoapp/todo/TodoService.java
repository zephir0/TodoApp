package com.example.todoapp.todo;

import com.example.todoapp.user.User;
import com.example.todoapp.user.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoService(TodoRepository todoRepository,

                       UserRepository userRepository) {
        this.todoRepository = todoRepository;

        this.userRepository = userRepository;
    }


    public void addTodoToList(TodoDto todoDto) {
        Todo todo = new Todo();
        todo.setDescription(todoDto.getDescription());
        todo.setUser(findUserId());
        showAllTodosDescriptions();
        todoRepository.save(todo);
    }

    public Collection<Todo> showAllTodosDescriptions() {
        User activeUser = findUserId();
        return activeUser.getTodoCollections();
//        return todoCollections
//                .stream()
//                .map(Todo::getDescription)
//                .collect(Collectors.toList());
    }

    String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    User findUserId() {
        Optional<User> byLogin = userRepository.findByLogin(getUsername());
        return byLogin.get();
    }
}
