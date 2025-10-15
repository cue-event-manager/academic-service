package cue.edu.co.model.academicarea.queries;

import cue.edu.co.model.common.queries.PaginationQuery;

import java.util.Optional;

public record AcademicAreaPaginationQuery(
        Optional<String> name,
        PaginationQuery pagination
) {
}
