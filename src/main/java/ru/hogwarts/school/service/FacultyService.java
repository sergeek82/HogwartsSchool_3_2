package ru.hogwarts.school.service;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public Faculty createFaculty (@NotNull Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Optional<Faculty> getFaculty (Long id) {
        return facultyRepository.findById(id);
    }

    public void updateFaculty (Faculty faculty) {
        facultyRepository.save(faculty);
    }

    public void deleteFaculty (Long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findAllByColor (String color) {
        return facultyRepository.findFacultyByColorContainsIgnoreCase(color);
    }

    public Collection<Faculty> findAllByName (String name) {
        return facultyRepository.findFacultyByNameContainsIgnoreCase(name);
    }

    public Optional<Faculty> findByStudent (long studentId) {
        return Optional.ofNullable(facultyRepository.findFacultyByStudentsId(studentId));
    }
}
