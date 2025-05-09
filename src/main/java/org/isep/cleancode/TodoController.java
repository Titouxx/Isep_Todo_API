package org.isep.cleancode;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private static final int MAX_NAME_LENGTH = 64;
    private final List<Todo> todos = new CopyOnWriteArrayList<>();

    @GetMapping
    public List<Todo> getAllTodos() {
        return todos;
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody Todo todo) {
        try {
            // Validate name
            if (todo.getName() == null || todo.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("Name is required");
            }
            if (todo.getName().length() > MAX_NAME_LENGTH) {
                throw new IllegalArgumentException(
                    "Name must be " + MAX_NAME_LENGTH + " characters or fewer"
                );
            }
            
            // Validate uniqueness
            if (todos.stream().anyMatch(t -> t.getName().equals(todo.getName()))) {
                throw new IllegalArgumentException("Todo name must be unique");
            }
            
            todos.add(todo);
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