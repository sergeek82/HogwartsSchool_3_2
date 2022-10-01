package ru.hogwarts.school.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.Optional;

import static java.lang.String.format;

@Slf4j
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
}
