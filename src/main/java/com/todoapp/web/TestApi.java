package com.todoapp.web;

import com.todoapp.todo.TodoService;
import com.todoapp.user.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//
//JUST TESTING APIs
//
@RestController
public class TestApi {
    private final TodoService todoService;
    private final UserService userService;


    public TestApi(TodoService todoService,
                   UserService userService) {
        this.todoService = todoService;
        this.userService = userService;
    }


    @GetMapping("/testapi2")
    @ResponseBody()
    public boolean testApi2() {
        return userService.isLoggedUserAdmin();
    }
}
