package cue.edu.co.model.faculty.exceptions;

import cue.edu.co.model.common.BusinessException;

public class FacultyHasAssociatedProgramsException extends BusinessException {
    private static final String MESSAGE = "Faculty cannot be deleted because it has associated academic programs";
    private static final String CODE = "FACULTY_HAS_PROGRAMS";

    public FacultyHasAssociatedProgramsException() {
        super(MESSAGE, CODE);
    }

    public FacultyHasAssociatedProgramsException(Long facultyId) {
        super(MESSAGE + " (Faculty ID: " + facultyId + ")", CODE);
    }
}
