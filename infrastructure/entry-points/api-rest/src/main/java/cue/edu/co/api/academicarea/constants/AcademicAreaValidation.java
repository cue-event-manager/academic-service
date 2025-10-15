package cue.edu.co.api.academicarea.constants;

public class AcademicAreaValidation {
    private AcademicAreaValidation() {}

    public static final String NAME_REQUIRED = "Academic area name is required";
    public static final String NAME_SIZE = "Academic area name must be between 1 and 100 characters";
    public static final String DESCRIPTION_SIZE = "Academic area description must not exceed 500 characters";
}
