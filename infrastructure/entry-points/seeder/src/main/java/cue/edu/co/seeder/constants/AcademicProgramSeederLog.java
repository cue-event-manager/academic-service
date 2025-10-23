package cue.edu.co.seeder.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AcademicProgramSeederLog {
    STARTING("Starting academic program seeder..."),
    FACULTY_NOT_FOUND("Faculty not found for program: {}"),
    PROGRAM_FOUND("Program already exists: {}"),
    PROGRAM_CREATED("Program created: {}"),
    FINISHED("Academic program seeder finished.");

    private final String message;
}