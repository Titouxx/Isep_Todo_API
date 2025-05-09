package org.isep.cleancode.persistence;

import org.isep.cleancode.Todo;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TodoRepository {
    private static final List<Todo> todos = new CopyOnWriteArrayList<>();

    public void addTodo(Todo todo) {
        todos.add(todo);
    }

    public List<Todo> getAllTodos() {
        return todos;
    }

    public boolean existsByName(String name) {
        return todos.stream().anyMatch(t -> t.getName().equals(name));
    }
}