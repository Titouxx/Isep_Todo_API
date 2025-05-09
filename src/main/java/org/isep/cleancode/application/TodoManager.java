package org.isep.cleancode.application;

import java.util.List;

import org.isep.cleancode.Todo;
import org.isep.cleancode.persistence.TodoRepository;

public class TodoManager {
    private static final int MAX_NAME_LENGTH = 64;
    private final TodoRepository todoRepository;

    public TodoManager(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void createTodo(Todo todo) {
        validateTodo(todo);
        todoRepository.addTodo(todo);
    }

    public List<Todo> getAllTodos() {
        return todoRepository.getAllTodos();
    }

    private void validateTodo(Todo todo) {
        if (todo.getName() == null || todo.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (todo.getName().length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Name must be " + MAX_NAME_LENGTH + " characters or fewer");
        }
        if (todoRepository.existsByName(todo.getName())) {
            throw new IllegalArgumentException("Todo name must be unique");
        }
    }
}