package cue.edu.co.model.academicarea.commands;

import cue.edu.co.model.academicarea.AcademicArea;

import java.time.LocalDateTime;

public record CreateAcademicAreaCommand(
        String name,
        String description
) {
    public AcademicArea toDomain() {
        return AcademicArea.builder()
                .name(name)
                .description(description)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
