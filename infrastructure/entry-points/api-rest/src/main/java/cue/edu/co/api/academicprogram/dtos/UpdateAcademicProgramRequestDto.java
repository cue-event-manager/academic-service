package cue.edu.co.api.academicprogram.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import static cue.edu.co.api.academicprogram.constants.AcademicProgramValidation.*;
import static cue.edu.co.model.academicprogram.constants.AcademicProgramConstant.*;

public record UpdateAcademicProgramRequestDto(
        @NotBlank(message = NAME_REQUIRED)
        @Size(max = MAX_NAME_LENGTH, message = NAME_SIZE)
        String name,

        @Size(max = MAX_DESCRIPTION_LENGTH, message = DESCRIPTION_SIZE)
        String description,

        @NotNull(message = FACULTY_ID_REQUIRED)
        Long facultyId
) {
}
