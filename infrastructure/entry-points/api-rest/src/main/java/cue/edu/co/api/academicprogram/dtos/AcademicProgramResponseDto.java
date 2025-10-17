package cue.edu.co.api.academicprogram.dtos;

import java.time.LocalDateTime;

public record AcademicProgramResponseDto(
        Long id,
        String name,
        String description,
        FacultyResponseDto faculty,
        LocalDateTime createdAt
) {
    public record FacultyResponseDto(
            Long id,
            String name
    ) {}
}
