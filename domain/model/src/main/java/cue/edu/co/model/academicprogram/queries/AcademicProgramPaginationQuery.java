package cue.edu.co.model.academicprogram.queries;

import cue.edu.co.model.common.queries.PaginationQuery;

import java.util.Optional;

public record AcademicProgramPaginationQuery(
        Optional<String> name,
        Optional<Long> facultyId,
        PaginationQuery pagination
) {
}
