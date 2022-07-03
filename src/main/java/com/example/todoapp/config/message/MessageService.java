package com.example.todoapp.config.message;

import com.example.todoapp.user.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public String getWelcomeMessage() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName().toUpperCase();
        String message = ", WELCOME IN TodoApp";
        return username + message;
    }
}
