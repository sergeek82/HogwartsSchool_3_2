package ru.hogwarts.school;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.controller.StudentController;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HogwartsSchool32ApplicationTests {

    @Autowired
    private StudentController studentController;
    @Autowired
    FacultyController facultyController;
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void contextLoads () {
        assertNotNull(studentController);
        assertNotNull(facultyController);
    }
}
