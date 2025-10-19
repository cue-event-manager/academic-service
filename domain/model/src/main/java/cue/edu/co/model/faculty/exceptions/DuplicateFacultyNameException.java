package cue.edu.co.model.faculty.exceptions;

import cue.edu.co.model.common.BusinessException;

public class DuplicateFacultyNameException extends BusinessException {
    private static final String MESSAGE = "Faculty name already exists";
    private static final String CODE = "FACULTY_DUPLICATE_NAME";

    public DuplicateFacultyNameException() {
        super(MESSAGE, CODE);
    }

    public DuplicateFacultyNameException(String name) {
        super(MESSAGE + ": " + name, CODE);
    }
}
