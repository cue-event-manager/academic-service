package cue.edu.co.api.faculty.dtos;

import java.time.LocalDateTime;

public record FacultyResponseDto(
        Long id,
        String name,
        String description,
        LocalDateTime createdAt
) {
}
