package ru.hogwarts.school.service;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        return studentRepository.findAllByAgeContaining(age);
    }

    public List<Student> getAgeBetween (int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public Set<Student> getAllByFaculty (long id) {
        return studentRepository.findStudentsByFaculty_Id(id);
    }
}
