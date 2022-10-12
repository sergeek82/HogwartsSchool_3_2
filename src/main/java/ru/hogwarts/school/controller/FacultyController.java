package ru.hogwarts.school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Objects;

/**
 * Controller to interact with Faculties
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    /**
     * EndPoint to see difference between parallel and not parallel stream
     */
    @GetMapping("/getInt")
    public String getInt () {
        long start = System.currentTimeMillis();
        Integer notParallel = facultyService.returnInt();
        long notParallelEnd = System.currentTimeMillis() - start;
        start = System.currentTimeMillis();
        Integer parallel = facultyService.returnIntParallel();
        long parallelEnd = System.currentTimeMillis() - start;
        return String.format("Output facultyService.returnInt() is: %d  it takes %d%n" +
                        "Output facultyService.returnIntParallel(); is %d  it takes %d",
                notParallel,
                notParallelEnd,
                parallel,
                parallelEnd);
    }

    /**
     * EndPoint to find the longest name
     */
    @GetMapping("/longestName")
    public String getLongestName () {
        return facultyService.findLongestName();
    }

    /**
     * EndPoint to find faculty by id
     *
     * @param id faculty id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getEntityById (@PathVariable Long id) {
        return ResponseEntity.of(facultyService.getFaculty(id));
    }

    /**
     * EndPoint to find faculty by student id
     *
     * @param id student id
     */
    @GetMapping("/byStudentId")
    public ResponseEntity<Faculty> getFacultyByStudent (@RequestParam(value = "studentId") long id) {
        return ResponseEntity.of(facultyService.findByStudent(id));
    }

    /**
     * saving faculty
     */
    @PostMapping
    public Faculty addEntity (@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    /**
     * EndPoint to find faculty name or color
     *
     * @param name  faculty name
     * @param color faculty color
     */
    @GetMapping("/byNameOrColor")
    public ResponseEntity<Collection<Faculty>> findAllByNameOrColor (
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "color", required = false) String color) {
        boolean nameIs = Objects.nonNull(name) && !name.isBlank() && !name.isEmpty();
        boolean colorIs =
                Objects.nonNull(color) && !color.isBlank() && !color.isEmpty() || name.isEmpty() || name.isBlank();
        if (nameIs) {
            return ResponseEntity.ok(facultyService.findAllByName(name));
        }
        if (colorIs) {
            return ResponseEntity.ok(facultyService.findAllByColor(color));
        }
        return ResponseEntity.status(400).build();
    }

    /**
     * updating faculty
     */
    @PutMapping
    public void updateEntity (@RequestBody Faculty faculty) {
        facultyService.updateFaculty(faculty);
    }

    /**
     * deleting faculty
     */
    @DeleteMapping("/{id}")
    public void deleteEntity (@PathVariable Long id) {
        facultyService.deleteFaculty(id);
    }
}
