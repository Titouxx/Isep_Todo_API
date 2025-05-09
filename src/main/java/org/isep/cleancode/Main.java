package org.isep.cleancode;

import org.isep.cleancode.application.TodoManager;
import org.isep.cleancode.application.ITodoRepository;
import org.isep.cleancode.persistence.TodoRepository;
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
        return new TodoRepository();
    }

    @Bean
    public TodoManager todoManager(ITodoRepository todoRepository) {
        return new TodoManager(todoRepository);
    }

    @Bean
    public TodoController todoController(TodoManager todoManager) {
        return new TodoController(todoManager);
    }
}