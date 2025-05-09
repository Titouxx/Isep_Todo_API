package org.isep.cleancode.persistence;

import org.isep.cleancode.Todo;
import org.isep.cleancode.application.ITodoRepository;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TodoRepository implements ITodoRepository {
    private static final List<Todo> todos = new CopyOnWriteArrayList<>();

    @Override
    public void addTodo(Todo todo) {
        todos.add(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todos;
    }

    @Override
    public boolean existsByName(String name) {
        return todos.stream().anyMatch(t -> t.getName().equals(name));
    }
}