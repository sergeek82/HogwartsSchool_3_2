package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findAllByAge (int age);

    Collection<Student> findByAgeBetween (int min, int max);

    Collection<Student> findStudentsByFaculty_Id (Long id);

    @Query(value = "SELECT COUNT(*) AS amount FROM student", nativeQuery = true)
    Integer getAmount ();

    @Query(value = "SELECT AVG(age) AS average_age FROM student", nativeQuery = true)
    Double getAverageAge ();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT  5", nativeQuery = true)
    Collection<Student> getTailFive ();
}
