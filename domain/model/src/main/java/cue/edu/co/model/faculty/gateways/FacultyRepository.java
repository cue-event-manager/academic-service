package cue.edu.co.model.faculty.gateways;

import cue.edu.co.model.common.results.PageResult;
import cue.edu.co.model.faculty.Faculty;
import cue.edu.co.model.faculty.queries.FacultyPaginationQuery;

import java.util.Optional;

public interface FacultyRepository {
    Faculty save(Faculty faculty);
    Optional<Faculty> findById(Long id);
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
    PageResult<Faculty> findAllByFilters(FacultyPaginationQuery query);
    void deleteById(Long id);
    boolean hasActiveAcademicPrograms(Long facultyId);
}
