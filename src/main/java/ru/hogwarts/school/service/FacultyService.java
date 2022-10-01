package ru.hogwarts.school.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Optional;

import static java.lang.String.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class FacultyService {
    private final String PATTERN = "Method %s of Faculty Service Class was invoked";

    private final FacultyRepository facultyRepository;

    public Faculty createFaculty (@NotNull Faculty faculty) {
        log.info(format(PATTERN, "createFaculty({})"), faculty);
        return facultyRepository.save(faculty);
    }

    public Optional<Faculty> getFaculty (Long id) {
        log.info(format(PATTERN, "getFaculty({})"), id);
        return facultyRepository.findById(id);
    }

    public void updateFaculty (Faculty faculty) {
        log.info(format(PATTERN, "updateFaculty({})"), faculty);
        facultyRepository.save(faculty);
    }

    public void deleteFaculty (Long id) {
        log.info(format(PATTERN, "deleteFaculty({})"), id);
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findAllByColor (String color) {
        log.info(format(PATTERN, "findAllByColor({})"), color);
        return facultyRepository.findFacultyByColorContainsIgnoreCase(color);
    }

    public Collection<Faculty> findAllByName (String name) {
        log.info(format(PATTERN, " findAllByName({})"), name);
        return facultyRepository.findFacultyByNameContainsIgnoreCase(name);
    }

    public Optional<Faculty> findByStudent (long studentId) {
        log.info(format(PATTERN, "findByStudent({})"), studentId);
        return Optional.ofNullable(facultyRepository.findFacultyByStudentsId(studentId));
    }
}
