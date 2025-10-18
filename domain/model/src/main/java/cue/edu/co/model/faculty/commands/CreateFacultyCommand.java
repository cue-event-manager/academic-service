package cue.edu.co.model.faculty.commands;

import cue.edu.co.model.faculty.Faculty;

import java.time.LocalDateTime;

public record CreateFacultyCommand(
        String name,
        String description
) {
    public Faculty toDomain() {
        return Faculty.builder()
                .name(name)
                .description(description)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
