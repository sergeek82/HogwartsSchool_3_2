package ru.hogwarts.school.service;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public void updateStudent (Student student) {
        studentRepository.updateStudent(student.getName(), student.getAge(), student.getId());
    }

    public void deleteStudent (Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getByAge (int age) {
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }
}
