package ru.hogwarts.school.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final Map<Long, Student> studentMap = new HashMap<>();
    private long idCount = 0;

    public Student createStudent (@NotNull Student student) {
        ++idCount;
        student.setId(idCount);
        studentMap.put(idCount, student);
        return student;
    }

    public Optional<Student> getStudent (long id) {
        return Optional.ofNullable(studentMap.get(id));
    }

    public Student updateStudent (Student student) {
        studentMap.put(student.getId(), student);
        return student;
    }

    public Optional<Student> deleteStudent (Long id) {
        return Optional.ofNullable(studentMap.remove(id));
    }

    public List<Student> getByAge (int age) {
        return studentMap.values().stream().filter(e -> Objects.equals(e.getAge(), age)).collect(Collectors.toList());
    }
}
