package cue.edu.co.api.academicprogram.constants;

public class AcademicProgramValidation {
    private AcademicProgramValidation() {}

    public static final String NAME_REQUIRED = "Academic program name is required";
    public static final String NAME_SIZE = "Academic program name must be between 1 and 100 characters";
    public static final String DESCRIPTION_SIZE = "Academic program description must not exceed 255 characters";
    public static final String FACULTY_ID_REQUIRED = "Faculty ID is required";
}
