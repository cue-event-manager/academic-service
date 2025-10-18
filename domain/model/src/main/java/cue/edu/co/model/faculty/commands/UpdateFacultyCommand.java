package cue.edu.co.model.faculty.commands;

import cue.edu.co.model.faculty.Faculty;

public record UpdateFacultyCommand(
        Long id,
        String name,
        String description
) {
    public Faculty toDomain(Faculty existing) {
        return Faculty.builder()
                .id(existing.getId())
                .name(name)
                .description(description)
                .createdAt(existing.getCreatedAt())
                .build();
    }
}
