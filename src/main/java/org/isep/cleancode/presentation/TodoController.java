package org.isep.cleancode.presentation;

import java.util.List;

import org.isep.cleancode.Todo;
import org.isep.cleancode.application.TodoManager;
import org.isep.cleancode.persistence.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoManager todoManager = new TodoManager(new TodoRepository());

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoManager.getAllTodos();
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody Todo todo) {
        try {
            todoManager.createTodo(todo);
            return ResponseEntity.status(HttpStatus.CREATED).body(todo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    private static class ErrorResponse {
        private final String error;
        
        public ErrorResponse(String error) {
            this.error = error;
        }
        
        public String getError() {
            return error;
        }
    }
}