package ru.hogwarts.school.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final Map<Long, Faculty> facultyMap = new HashMap<>();
    long idCount = 0;

    public Faculty createFaculty (@NotNull Faculty faculty) {
        faculty.setId(++idCount);
        facultyMap.put(idCount, faculty);
        return faculty;
    }

    public Optional<Faculty> getFaculty (Long id) {
        return Optional.ofNullable(facultyMap.get(id));
    }

    public Faculty updateFaculty (Faculty faculty) {
        facultyMap.put(faculty.getId(), faculty);
        return faculty;
    }

    public Optional<Faculty> deleteFaculty (Long id) {
        return Optional.ofNullable(facultyMap.remove(id));
    }

    public List<Faculty> getByColor (String color) {
        return facultyMap.values().stream().filter(e -> e.getColor().equals(color)).collect(Collectors.toList());
    }
}
