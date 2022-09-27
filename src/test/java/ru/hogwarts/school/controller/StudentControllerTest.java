package ru.hogwarts.school.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import ru.hogwarts.school.model.Student;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {
    @LocalServerPort
    int port;
    @Autowired
    private StudentController studentController;
    @Autowired
    TestRestTemplate testRestTemplate;
    private final String URL = "http://localhost:";
    private final String MAPPING = "/student";
    private final String ID = "?id={val}";
    private final String AGE = "/byAge?age={val}";
    private final String RANGE = "/byAgeBetween?min={val}&max={val}";
    private final String FACULTY = "/byFaculty?facultyId={id}";

    @Test
    public void getById_ShouldReturnEntityById () {
        assertNotNull(testRestTemplate.getForObject(URL + port + MAPPING + ID, Student.class, 1));
        assertNull((testRestTemplate.<Student>getForObject(URL + port + MAPPING + ID, Student.class, 11).getId()));
    }

    @Test
    public void getByAge_ShouldReturnListByAge () {
        assertEquals(
                "[{\"id\":1,\"name\":\"Harry Potter \",\"age\":20,\"faculty\":{\"id\":2,\"name\":\"Gryffindor \",\"color\":\"red\"}}]",
                testRestTemplate.getForObject(URL + port + MAPPING + AGE, String.class, 20));
        assertEquals("[]", testRestTemplate.getForObject(URL + port + MAPPING + AGE, String.class, 11));
    }

    @Test
    public void findAllEntityWhereAgeBetween_ShouldReturnListAgeBetween () {
        assertEquals(
                "[{\"id\":1,\"name\":\"Harry Potter \",\"age\":20,\"faculty\":{\"id\":2,\"name\":\"Gryffindor \",\"color\":\"red\"}}]",
                testRestTemplate.getForObject(URL + port + MAPPING + RANGE, String.class, 20, 21));
        assertEquals("[]", testRestTemplate.getForObject(URL + port + MAPPING + RANGE, String.class, 33, 44));
    }

    @Test
    public void findAllEntityRelatedToFaculty_ShouldReturnSetEntityByFaculty () {
        assertEquals(
                "[{\"id\":7,\"name\":\"Pansy Parkinson\",\"age\":22,\"faculty\":{\"id\":3,\"name\":\"Hufflepuff \",\"color\":\"yellow\"}}]",
                testRestTemplate.getForObject(URL + port + MAPPING + FACULTY, String.class, 3));
        assertEquals("[]", testRestTemplate.getForObject(URL + port + MAPPING + FACULTY, String.class, 33));
    }

    @Test
    public void updateEntity_ShouldUpdateEntity () throws JsonProcessingException, JSONException {
        Student student = new Student(29L, "String", 82, null);
        HttpHeaders headers = new HttpHeaders();
        ;
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(student);
        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response =
                testRestTemplate.exchange(URL + port + MAPPING, HttpMethod.PUT, httpEntity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void addEntity_ShouldAddEntityToDatabase () {
        Student student = new Student(10L, "student", 88, null);
        assertNotNull(testRestTemplate.postForObject(URL + port + MAPPING, student, String.class));
    }

    @Test
    public void deleteEntity_ShouldRemoveEntityFomDatabase () {
        ResponseEntity<?> response =
                testRestTemplate.exchange(URL + port + MAPPING + ID, HttpMethod.DELETE, null, Void.class, 22);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}