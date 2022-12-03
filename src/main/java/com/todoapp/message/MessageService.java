package com.todoapp.message;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public String getWelcomeMessage() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String message = ", welcome in TodoApp";
        return username + message;
    }
}
