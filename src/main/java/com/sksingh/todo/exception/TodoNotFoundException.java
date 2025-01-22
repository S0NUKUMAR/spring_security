package com.sksingh.todo.exception;

public class TodoNotFoundException extends RuntimeException{
    public TodoNotFoundException(Long id){
        super("Todo Not found with Id: " + id);
    }
}

