package ru.hogwarts.school.service;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public Student createStudent (@NotNull Student student) {
        studentRepository.save(student);
        return student;
    }

    public Optional<Student> getStudent (long id) {
        return studentRepository.findById(id);
    }

    public void updateStudentById (@NotNull Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent (Long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getByAge (int age) {
        return studentRepository.findAllByAge(age);
    }

    public Collection<Student> getAgeBetween (int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public Collection<Student> getAllByFaculty (Long id) {
        return studentRepository.findStudentsByFaculty_Id(id);
    }

    public Integer getStudentAmount () {
        return studentRepository.getAmount();
    }

    public Double getAverageAge () {
        return studentRepository.getAverageAge();
    }

    public Collection<Student> getLastFive () {
        return studentRepository.getTailFive();
    }
}
