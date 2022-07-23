package com.example.todoapp.exceptions;

public class CannotDeleteAccountExcepetion extends RuntimeException{
    public CannotDeleteAccountExcepetion(String message) {
        super(message);
    }
}
