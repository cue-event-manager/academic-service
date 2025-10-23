package cue.edu.co.model.academicarea.gateways;

import cue.edu.co.model.academicarea.AcademicArea;
import cue.edu.co.model.academicarea.queries.AcademicAreaPaginationQuery;
import cue.edu.co.model.common.results.PageResult;

import java.util.List;
import java.util.Optional;

public interface AcademicAreaRepository {
    AcademicArea save(AcademicArea academicArea);
    Optional<AcademicArea> findById(Long id);
    Optional<AcademicArea> findByName(String name);
    List<AcademicArea> findAll();
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
    void deleteById(Long id);
    PageResult<AcademicArea> findAllByFilters(AcademicAreaPaginationQuery query);
}
