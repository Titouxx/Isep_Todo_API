package org.isep.cleancode;

import org.isep.cleancode.application.ITodoRepository;
import org.isep.cleancode.application.TodoManager;
import org.isep.cleancode.persistence.csv.TodoCsvFilesRepository;
import org.isep.cleancode.presentation.TodoController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public ITodoRepository todoRepository() {
        return new TodoCsvFilesRepository();
    }

    @Bean
    public TodoController todoController(ITodoRepository todoRepository) {
        return new TodoController(new TodoManager(todoRepository));
    }
}