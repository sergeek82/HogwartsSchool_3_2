package ru.hogwarts.school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

/**
 * Controller to interact with Students
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    /**
     * saving student
     */
    @PostMapping
    public Student addEntity (@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    /**
     * updating student
     */
    @PutMapping
    public void updateEntity (@RequestBody Student student) {
        studentService.updateStudentById(student);
    }

    /**
     * deleting student
     */
    @DeleteMapping
    public void deleteEntity (@RequestParam Long id) {
        studentService.deleteStudent(id);
    }

    /**
     * finding a student by id
     *
     * @param id student id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Student> getEntityById (@PathVariable Long id) {
        return ResponseEntity.of(studentService.getStudent(id));
    }

    /**
     * finding a students by age
     *
     * @param age student age
     */
    @GetMapping("/byAge")
    public Collection<Student> findAllByAge (@RequestParam(value = "age") int age) {
        return studentService.getByAge(age);
    }

    /**
     * finding a students by age  spawn
     *
     * @param min student min age limit
     * @param max student max age limit
     */
    @GetMapping("/byAgeBetween")
    public Collection<Student> findAllEntityWhereAgeBetween (@RequestParam(value = "min") int min,
                                                             @RequestParam(value = "max") int max) {
        return studentService.getAgeBetween(min, max);
    }

    /**
     * search student list by faculty id
     *
     * @param facultyId faculty id
     */
    @GetMapping("/byFaculty")
    public Collection<Student> findAllEntityRelatedToFaculty (@RequestParam(value = "facultyId") Long facultyId) {
        return studentService.getAllByFaculty(facultyId);
    }

    /**
     * counting students
     */
    @GetMapping("/amount")
    public Integer countStudents () {
        return studentService.getStudentAmount();
    }

    /**
     * getting average age
     */
    @GetMapping("/averageAge")
    public Double getAverageAge () {
        return studentService.getAverageAge();
    }

    /**
     * getting tail of the list
     */
    @GetMapping("/lastFive")
    public Collection<Student> getLastFiveStudents () {
        return studentService.getLastFive();
    }

    /**
     * getting list ao students that names start with A using filter in a stream
     */
    @GetMapping("/beginWithA")
    public Collection<Student> getListNameBeginWithA () {
        return studentService.findByNameStartWithA();
    }

    /**
     * getting average age calculated in a stream
     */
    @GetMapping("/avgAge")
    public Double averageStudentAge () {
        return studentService.findAverageAgeUsingStream();
    }

    /**
     * printing students names in different not synchronized threads
     */
    @GetMapping("/threads")
    public void printNamesInDifferentThreads () {
        studentService.getStudentNamesInThreads();
    }

    /**
     * printing students names in different  synchronized threads
     */
    @GetMapping("/synchroThreads")
    public void printNamesSynchro () {
        studentService.getStudentNamesInSynchroThreads();
    }
}
