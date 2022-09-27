package ru.hogwarts.school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public Student addEntity (@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public void updateEntity (@RequestBody Student student) {
         studentService.updateStudentById(student);
    }

    @DeleteMapping
    public void deleteEntity (@RequestParam Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getEntityById (@PathVariable Long id) {
        return ResponseEntity.of(studentService.getStudent(id));
    }

    @GetMapping("/byAge")
    public Collection<Student> findAllByAge (@RequestParam(value = "age") int age) {
        return studentService.getByAge(age);
    }

    @GetMapping("/byAgeBetween")
    public Collection<Student> findAllEntityWhereAgeBetween (@RequestParam(value = "min") int min,
                                                             @RequestParam(value = "max") int max) {
        return studentService.getAgeBetween(min, max);
    }

    @GetMapping("/byFaculty")
    public Collection<Student> findAllEntityRelatedToFaculty (@RequestParam(value = "facultyId") Long facultyId) {
        return studentService.getAllByFaculty(facultyId);
    }
}
