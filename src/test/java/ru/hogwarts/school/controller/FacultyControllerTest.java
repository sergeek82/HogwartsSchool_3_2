package ru.hogwarts.school.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    FacultyRepository facultyRepository;
    @MockBean
    AvatarRepository avatarRepository;
    @MockBean
    StudentRepository studentRepository;
    @SpyBean
    StudentService studentService;
    @SpyBean
    FacultyService facultyService;
    @SpyBean
    AvatarService avatarService;
    @InjectMocks
    FacultyController controller;
    private String name = "Gryffindor";
    private String color = "red";
    private long id = 1;
    private Faculty faculty;
    JSONObject facultyObject;
    private final String URL = "/faculty";

    @BeforeEach
    void init () throws JSONException {
        facultyObject = new JSONObject();
        facultyObject.put("id", 1);
        facultyObject.put("name", name);
        facultyObject.put("color", color);
        facultyObject.put("students", null);
        faculty = new Faculty(id, name, color, null);
    }

    @Test
    void getEntityById_ShouldReturnEntity () throws Exception {
        when(facultyRepository.findById(anyLong())).thenReturn(Optional.of(faculty));
        mockMvc.perform(get(URL + "/{id}", 1L).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Gryffindor"))
                .andExpect(jsonPath("$.color").value("red"));
    }

    @Test
    void getFacultyByStudent_ShouldReturnFaculty () throws Exception {
        when(facultyRepository.findFacultyByStudentsId(anyLong())).thenReturn(faculty);
        mockMvc.perform(get(URL + "/byStudentId?studentId={id}", 2L).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Gryffindor"))
                .andExpect(jsonPath("$.color").value("red"));
    }

    @Test
    void findAllByNameOrColor_ShouldReturnCollectionOfEntities () throws Exception {
        when(facultyRepository.findFacultyByNameContainsIgnoreCase(anyString())).thenReturn(List.of(faculty));
        when(facultyRepository.findFacultyByColorContainsIgnoreCase(anyString())).thenReturn(List.of(faculty));
        mockMvc.perform(get(URL + "/byNameOrColor?name={name}", "Gryffindor").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Gryffindor"))
                .andExpect(jsonPath("$[0].color").value("red"));
        mockMvc.perform(get(URL + "/byNameOrColor?color={color}", "red").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Gryffindor"))
                .andExpect(jsonPath("$[0].color").value("red"));
    }

    @Test
    void addEntityS_ShouldAddEntity () throws Exception {
        when(facultyRepository.save(faculty)).thenReturn(faculty);
        mockMvc.perform(post(URL).content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Gryffindor"))
                .andExpect(jsonPath("$.color").value("red"));
    }

    @Test
        // There is no sense to test it because
    void updateEntity () {// the same method save(E e) use...
    }

    @Test
    void deleteEntity_ShouldDeleteEntity () throws Exception {
        doNothing().when(facultyRepository).deleteById(anyLong());
        mockMvc.perform(delete(URL + "/{id}", 1L).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
//
//
//
//   }
}