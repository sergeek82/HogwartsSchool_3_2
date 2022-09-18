package ru.hogwarts.school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    @PostMapping
    public Faculty addEntity (@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getEntityById (@PathVariable Long id) {
        return ResponseEntity.of(facultyService.getFaculty(id));
    }

    @PutMapping
    public Faculty updateEntity (@RequestBody Faculty faculty) {
        return facultyService.updateFaculty(faculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteEntity (@PathVariable Long id) {
        return ResponseEntity.of(facultyService.deleteFaculty(id));
    }

    @GetMapping("byColor/{color}")
    public List<Faculty> findAllByColor (@PathVariable("color") String color) {
        return facultyService.getByColor(color);
    }
}
