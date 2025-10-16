package cue.edu.co.api.faculty.constants;

public class FacultyValidation {
    private FacultyValidation() {}

    public static final String NAME_REQUIRED = "Faculty name is required";
    public static final String NAME_SIZE = "Faculty name must be between 1 and 100 characters";
    public static final String DESCRIPTION_SIZE = "Faculty description must not exceed 255 characters";
}
