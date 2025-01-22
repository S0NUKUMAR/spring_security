package com.sksingh.todo.service;


import com.sksingh.todo.exception.TodoNotFoundException;
import com.sksingh.todo.exception.TodoValidationException;
import com.sksingh.todo.model.Todo;
import com.sksingh.todo.respository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    TodoRepository todoRepository;

    public List<Todo> getAllTodo() {
        return todoRepository.findAll();
    }

    public Todo createTodo(Todo todo) {
        validateTodo(todo);
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Todo todo , Long id){
        Todo t = todoRepository.findById(id).orElseThrow(()-> new TodoNotFoundException(id));
        validateTodo(todo);
        t.setTitle(todo.getTitle());
        t.setDescription(todo.getDescription());
        t.setDueDate(todo.getDueDate());
        return todoRepository.save(t);
    }

    public Todo getTodo(Long id){
        return todoRepository.findById(id).orElseThrow(()-> new TodoNotFoundException(id));
    }

    public Todo deleteTodo(Long id){
        Todo t = todoRepository.findById(id).orElseThrow(()-> new TodoNotFoundException(id));
        todoRepository.deleteById(id);
        return t;
    }

    private void validateTodo(Todo todo) {
        List<String> errors = new ArrayList<>();

        if (todo.getTitle() == null || todo.getTitle().trim().isEmpty()) {
            errors.add("Title is required");
        }

        if (todo.getTitle() != null && todo.getTitle().length() > 100) {
            errors.add("Title must not exceed 100 characters");
        }

        if (todo.getDescription() != null && todo.getDescription().length() > 1000) {
            errors.add("Description must not exceed 1000 characters");
        }

        if (todo.getDueDate() == null){
            errors.add("Due Date is required");
        }

        if (!errors.isEmpty()) {
            throw new TodoValidationException(String.join(", ", errors));
        }
    }
}
