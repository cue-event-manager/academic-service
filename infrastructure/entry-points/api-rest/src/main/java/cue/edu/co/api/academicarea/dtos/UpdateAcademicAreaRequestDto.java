package cue.edu.co.api.academicarea.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import static cue.edu.co.api.academicarea.constants.AcademicAreaValidation.*;
import static cue.edu.co.model.academicarea.constants.AcademicAreaConstant.*;

public record UpdateAcademicAreaRequestDto(
        @NotBlank(message = NAME_REQUIRED)
        @Size(max = NAME_MAX_SIZE, message = NAME_SIZE)
        String name,

        @Size(max = DESCRIPTION_MAX_SIZE, message = DESCRIPTION_SIZE)
        String description
) {
}
