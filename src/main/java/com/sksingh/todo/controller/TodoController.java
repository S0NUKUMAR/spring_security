package com.sksingh.todo.controller;

import com.sksingh.todo.model.Todo;
import com.sksingh.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping
    public List<Todo> getAllTodos(){
        return todoService.getAllTodo();
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo){
        return todoService.createTodo(todo);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@RequestBody Todo todo , @PathVariable Long id){
        return todoService.updateTodo(todo , id);
    }

    @GetMapping("/{id}")
    public Todo getTodo(@PathVariable Long id){
        return todoService.getTodo(id);
    }

    @DeleteMapping("/{id}")
    public Todo deletTodo(@PathVariable Long id){
       return todoService.deleteTodo(id);
    }
}
