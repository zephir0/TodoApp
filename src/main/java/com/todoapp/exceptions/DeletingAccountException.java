package com.todoapp.exceptions;

public class DeletingAccountException extends RuntimeException{
    public DeletingAccountException(String message) {
        super(message);
    }
}
