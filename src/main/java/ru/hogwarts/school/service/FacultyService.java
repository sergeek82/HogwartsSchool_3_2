package ru.hogwarts.school.service;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.List;
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

    public List<Faculty> getByNameOrColor (String name, String color) {
        return facultyRepository.findAllByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }

    public Faculty findByStudent (long studentId) {
        return facultyRepository.findFacultyByStudentsId(studentId);
    }
}
