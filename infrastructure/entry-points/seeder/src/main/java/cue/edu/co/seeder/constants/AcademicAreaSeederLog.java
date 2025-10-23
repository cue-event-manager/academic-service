package cue.edu.co.seeder.constants;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AcademicAreaSeederLog {
    STARTING("Starting academic area seeder..."),
    AREA_FOUND("Academic area already exists: {}"),
    AREA_CREATED("Academic area created: {}"),
    FINISHED("Academic area seeder finished.");

    private final String message;
}