package org.isep.cleancode.persistence.csv;

import org.isep.cleancode.Todo;
import org.isep.cleancode.application.ITodoRepository;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

public class TodoCsvFilesRepository implements ITodoRepository {
    private final Path csvPath;
    private static final String CSV_HEADER = "name,due_date";

    public TodoCsvFilesRepository() {
        this.csvPath = Paths.get("data/todos.csv"); 
        System.out.println("CSV will be saved to: " + csvPath.toAbsolutePath());
        initCsvFile();
    }
    
    private void initCsvFile() {
        try {
            Files.createDirectories(csvPath.getParent());
            
            if (!Files.exists(csvPath)) {
                Files.write(csvPath, Collections.singleton(CSV_HEADER));
                System.out.println("Created new CSV file at: " + csvPath.toAbsolutePath());
            } else {
                System.out.println("Using existing CSV file at: " + csvPath.toAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("CRITICAL ERROR: Failed to initialize CSV file at " + csvPath);
            e.printStackTrace();
            throw new RuntimeException("CSV initialization failed", e);
        }
    }

    @Override
    public void addTodo(Todo todo) {
        try {
            String line = String.format("%s,%s",
                todo.getName(),
                todo.getDueDate() != null ? todo.getDueDate() : ""
            );
            Files.write(csvPath, Collections.singleton(line), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save Todo to CSV", e);
        }
    }

    @Override
    public List<Todo> getAllTodos() {
        try {
            List<String> lines = Files.readAllLines(csvPath);
            return lines.stream()
                .skip(1)
                .map(line -> {
                    String[] parts = line.split(",");
                    Todo todo = new Todo();
                    todo.setName(parts[0]);
                    if (!parts[1].isEmpty()) {
                        todo.setDueDate(LocalDate.parse(parts[1]));
                    }
                    return todo;
                })
                .toList();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read Todos from CSV", e);
        }
    }

    @Override
    public boolean existsByName(String name) {
        return getAllTodos().stream().anyMatch(t -> t.getName().equals(name));
    }
}