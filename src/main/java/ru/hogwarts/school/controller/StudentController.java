package ru.hogwarts.school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;
import java.util.Set;

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
    public void updateEntity (@RequestBody Student student) {
        studentService.updateStudent(student);
    }

    @DeleteMapping("{id}")
    public void deleteEntity (@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/byAge")
    public List<Student> findAllByAge (@RequestParam(value = "age") int age) {
        return studentService.getByAge(age);
    }

    @GetMapping("/byAgeBetween")
    public List<Student> findAllEntityWhereAgeBetween (@RequestParam(value = "min") int min,
                                                       @RequestParam(value = "max") int max) {
        return studentService.getAgeBetween(min, max);
    }

    @GetMapping("/byFaculty")
    public Set<Student> findAllEntityRelatedToFaculty (@RequestParam(value = "facultyId") long facultyId) {
        return studentService.getAllByFaculty(facultyId);
    }
}
