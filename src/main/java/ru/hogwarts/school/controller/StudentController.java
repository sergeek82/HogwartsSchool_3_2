package ru.hogwarts.school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public Student addEntity (@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getEntityById (@PathVariable long id) {
        return ResponseEntity.of(studentService.getStudent(id));
    }

    @PutMapping
    public Student updateEntity (@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteEntity (@PathVariable Long id) {
        return ResponseEntity.of(studentService.deleteStudent(id));
    }

    @GetMapping("byAge/{age}")
    public List<Student> findAllByAge (@PathVariable int age) {
        return studentService.getByAge(age);
    }
}
