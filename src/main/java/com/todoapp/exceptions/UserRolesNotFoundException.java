package com.todoapp.exceptions;

public class UserRolesNotFoundException extends RuntimeException{
    public UserRolesNotFoundException(String message) {
        super(message);
    }
}
