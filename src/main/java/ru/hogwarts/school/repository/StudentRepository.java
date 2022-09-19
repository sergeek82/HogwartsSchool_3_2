package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Modifying
    @Query("UPDATE Student s SET s.name=?1, s.age=?2 WHERE s.id=?3")
    void updateStudent (String name, int age, long id);
}
