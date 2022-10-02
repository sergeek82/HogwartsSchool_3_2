package ru.hogwarts.school.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Slf4j
@Profile("default")
@RequiredArgsConstructor
@Service
public class StudentService {
    private final String PATTERN = "Method %s of Student Service Class was invoked";
    private final StudentRepository studentRepository;

    public Student createStudent (@NotNull Student student) {
        log.info(format(PATTERN, "createStudent({})"), student);
        return studentRepository.save(student);
    }

    public Optional<Student> getStudent (long id) {
        log.info(format(PATTERN, "getStudent({})"), id);
        return studentRepository.findById(id);
    }

    public void updateStudentById (@NotNull Student student) {
        log.info(format(PATTERN, "updateStudentById({})"), student);
        studentRepository.save(student);
    }

    public void deleteStudent (Long id) {
        log.info(format(PATTERN, "deleteStudent({})"), id);
        studentRepository.deleteById(id);
    }

    public Collection<Student> getByAge (int age) {
        log.info(format(PATTERN, "getByAge({})"), age);
        return studentRepository.findAllByAge(age);
    }

    public Collection<Student> getAgeBetween (int min, int max) {
        log.info(format(PATTERN, "getAgeBetween({}, {})"), min, max);
        return studentRepository.findByAgeBetween(min, max);
    }

    public Collection<Student> getAllByFaculty (Long id) {
        log.info(format(PATTERN, "getAllByFaculty({})"), id);
        return studentRepository.findStudentsByFaculty_Id(id);
    }

    public Integer getStudentAmount () {
        log.info(format(PATTERN, "getStudentAmount()"));
        return studentRepository.getAmount();
    }

    public Double getAverageAge () {
        log.info(format(PATTERN, "getAverageAge()"));
        return studentRepository.getAverageAge();
    }

    public Collection<Student> getLastFive () {
        log.info(format(PATTERN, "getLastFive()"));
        return studentRepository.getTailFive();
    }

    public Collection<Student> findByNameStartWithA () {
        log.info("findByNameStartWithA() was invoked");
        return studentRepository.findAll()
                .stream()
                .filter(e -> e.getName().startsWith("A"))
                .peek(student -> student.setName(student.getName().toUpperCase()))
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());
    }

    public Double findAverageAgeUsingStream () {
        log.info("findAverageAgeUsingStream() was invoked");
        return studentRepository.findAll().stream().parallel().mapToInt(Student::getAge).average().orElse(0);
    }

    public void getStudentNamesInThreads () {
        List<Student> students = studentRepository.findAll();
        printNames(students);
    }

    public void getStudentNamesInSynchroThreads () {
        List<Student> students = studentRepository.findAll();
        synchronized (Collections.unmodifiableList(students)) {
            printNames(students);
        }
    }

    private void printNames (List<Student> students) {
        String messageOne = "Print name in 0 thread: {}";
        String messageTwo = "Print name in 1 thread: {}";
        String messageTree = "Print name in 2 thread: {}";
        log.info(messageOne, students.stream().limit(2).map(Student::getName).collect(Collectors.joining(" ")));
        new Thread(() -> log.info(messageTwo,
                students.stream().skip(2).limit(2).map(Student::getName).collect(Collectors.joining(" ")))).start();
        new Thread(() -> log.info(messageTree,
                students.stream().skip(4).limit(2).map(Student::getName).collect(Collectors.joining(" ")))).start();
    }
}
