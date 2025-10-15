package cue.edu.co.model.academicarea.commands;

import cue.edu.co.model.academicarea.AcademicArea;

public record UpdateAcademicAreaCommand(
        Long id,
        String name,
        String description
) {
    public AcademicArea toDomain(AcademicArea existing) {
        return AcademicArea.builder()
                .id(existing.getId())
                .name(name)
                .description(description)
                .createdAt(existing.getCreatedAt())
                .build();
    }
}
