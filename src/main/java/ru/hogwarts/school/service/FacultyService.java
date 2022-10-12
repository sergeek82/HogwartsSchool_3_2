package ru.hogwarts.school.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.String.format;

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

    public String findLongestName () {
        return facultyRepository.findAll()
                .stream()
                .map(Faculty::getName)
                .max(Comparator.comparing(String::length))
                .orElse("not found");
    }

    public Integer returnIntParallel () {
        return Stream.iterate(1, a -> a + 1).limit(1_000_000).parallel().reduce(0, Integer::sum);
    }

    public Integer returnInt () {
        return IntStream.rangeClosed(0, 1_000_000).reduce(0, Integer::sum);
    }
}
