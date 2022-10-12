package ru.hogwarts.school;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class HogwartsSchool32Application {

    public static void main (String[] args) {
        SpringApplication.run(HogwartsSchool32Application.class, args);
    }
}
