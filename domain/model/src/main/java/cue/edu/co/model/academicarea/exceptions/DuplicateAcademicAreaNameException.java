package cue.edu.co.model.academicarea.exceptions;

import cue.edu.co.model.common.BusinessException;

public class DuplicateAcademicAreaNameException extends BusinessException {
    private static final String MESSAGE = "Academic area name already exists";
    private static final String CODE = "AA_DUPLICATE_NAME";

    public DuplicateAcademicAreaNameException() {
        super(MESSAGE, CODE);
    }

    public DuplicateAcademicAreaNameException(String name) {
        super(MESSAGE + ": " + name, CODE);
    }
}
