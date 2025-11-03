package cue.edu.co.model.academicprogram.queries;

import java.util.Optional;

public record GetAllAcademicProgramsQuery(
        Optional<String> name,
        Optional<Long> facultyId
) {
}
