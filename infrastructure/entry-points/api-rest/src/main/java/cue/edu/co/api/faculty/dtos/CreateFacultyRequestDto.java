package cue.edu.co.api.faculty.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import static cue.edu.co.api.faculty.constants.FacultyValidation.*;
import static cue.edu.co.model.faculty.constants.FacultyConstant.*;

public record CreateFacultyRequestDto(
        @NotBlank(message = NAME_REQUIRED)
        @Size(max = MAX_NAME_LENGTH, message = NAME_SIZE)
        String name,

        @Size(max = MAX_DESCRIPTION_LENGTH, message = DESCRIPTION_SIZE)
        String description
) {
}
