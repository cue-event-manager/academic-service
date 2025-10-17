package cue.edu.co.model.academicprogram.exceptions;

import cue.edu.co.model.common.BusinessException;

public class DuplicateAcademicProgramNameException extends BusinessException {
    private static final String MESSAGE = "Academic program name already exists in this faculty";
    private static final String CODE = "ACADEMIC_PROGRAM_DUPLICATE_NAME";

    public DuplicateAcademicProgramNameException() {
        super(MESSAGE, CODE);
    }

    public DuplicateAcademicProgramNameException(String name) {
        super(MESSAGE + ": " + name, CODE);
    }
}
