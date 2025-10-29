package cue.edu.co.model.academicprogram.gateways;

import cue.edu.co.model.academicprogram.AcademicProgram;
import cue.edu.co.model.academicprogram.queries.AcademicProgramPaginationQuery;
import cue.edu.co.model.common.results.PageResult;

import java.util.List;
import java.util.Optional;

public interface AcademicProgramRepository {
    AcademicProgram save(AcademicProgram academicProgram);
    Optional<AcademicProgram> findById(Long id);
    Optional<AcademicProgram> findByName(String name);
    boolean existsById(Long id);
    boolean existsByNameAndFacultyId(String name, Long facultyId);
    boolean existsByNameAndFacultyIdAndIdNot(String name, Long facultyId, Long id);
    void deleteById(Long id);
    PageResult<AcademicProgram> findAllByFilters(AcademicProgramPaginationQuery query);
    List<AcademicProgram> findAll();
}
