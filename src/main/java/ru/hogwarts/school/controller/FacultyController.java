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
    public void updateEntity (@RequestBody Faculty faculty) {
        facultyService.updateFaculty(faculty);
    }

    @DeleteMapping("{id}")
    public void deleteEntity (@PathVariable Long id) {
        facultyService.deleteFaculty(id);
    }

    @GetMapping("byColor/")
    public List<Faculty> findAllByColor (@RequestParam(value = "color", required = false) String color) {
        return facultyService.getByColor(color);
    }
}
