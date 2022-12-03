package com.todoapp.todo;

import com.todoapp.user.UserService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserService userService;

    public TodoService(TodoRepository todoRepository,

                       UserService userService) {
        this.todoRepository = todoRepository;

        this.userService = userService;
    }


    public void addTaskToList(TodoDto todoDto) {
        Todo todo = new Todo();
        todo.setDescription(todoDto.getDescription());
        todo.setUser(userService.getLoggedUser());
        todoRepository.save(todo);
    }

    public Collection<Todo> getLoggedUserAllTasks() {
        return userService.getLoggedUser().getTodoCollections();
    }

    public List<Todo> reverseSortTaskList() {
        List<Todo> collect = new ArrayList<>(
                getLoggedUserAllTasks().
                        stream().
                        toList()
        );
        Collections.reverse(collect);
        return collect;
    }

    public List<Todo> sortTaskList() {
        return new ArrayList<>(
                getLoggedUserAllTasks().
                        stream().
                        toList()
        );
    }

    public List<Todo> sortingParam(String param) {
        try {
            if (param.equals("from oldest")) {
                return reverseSortTaskList();
            } else
                return sortTaskList();
        } catch (NullPointerException e) {
            return sortTaskList();
        }
    }


}
