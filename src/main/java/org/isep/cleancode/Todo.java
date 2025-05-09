package org.isep.cleancode;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Todo {
    private String name;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    // Required no-arg constructor
    public Todo() {}

    // Simple getters and setters without validation
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
}