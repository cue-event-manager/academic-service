package cue.edu.co.api.academicarea.dtos;

import java.time.LocalDateTime;

public record AcademicAreaResponseDto(
        Long id,
        String name,
        String description,
        LocalDateTime createdAt
) {
}
