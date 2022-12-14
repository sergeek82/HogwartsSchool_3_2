package ru.hogwarts.school.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Avatar;

import java.util.Optional;

@Repository
public interface AvatarRepository extends PagingAndSortingRepository<Avatar, Long> {

    @NotNull Optional<Avatar> findByStudentId (@NotNull Long studentId);
}
