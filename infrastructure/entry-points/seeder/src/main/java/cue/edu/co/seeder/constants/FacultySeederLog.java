package cue.edu.co.seeder.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FacultySeederLog {
    STARTING("Starting faculty seeder..."),
    FACULTY_FOUND("Faculty already exists: {}"),
    FACULTY_CREATED("Faculty created: {}"),
    FINISHED("Faculty seeder finished.");

    private final String message;
}