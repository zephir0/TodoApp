package com.example.todoapp.exceptions;

public class WrongOldPasswordException extends RuntimeException{
    public WrongOldPasswordException(String message) {
        super(message);
    }
}
