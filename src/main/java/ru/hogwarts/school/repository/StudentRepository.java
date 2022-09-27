package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Modifying
    @Query("UPDATE Student s SET s.name=?1, s.age=?2, s.faculty=?3 WHERE s.id=?4")
    void updateStudent (String name, Integer age, Faculty faculty, Long id);

    Collection<Student> findAllByAge (int age);

    Collection<Student> findByAgeBetween (int min, int max);

    Collection<Student> findStudentsByFaculty_Id (Long id);
}
