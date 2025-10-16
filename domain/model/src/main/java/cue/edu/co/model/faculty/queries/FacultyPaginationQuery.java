package cue.edu.co.model.faculty.queries;

import cue.edu.co.model.common.queries.PaginationQuery;

import java.util.Optional;

public record FacultyPaginationQuery(
        Optional<String> name,
        PaginationQuery pagination
) {
}
