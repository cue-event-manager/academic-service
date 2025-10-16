package cue.edu.co.model.academicprogram.exceptions;

import cue.edu.co.model.common.NotFoundException;

public class AcademicProgramNotFoundException extends NotFoundException {
    private static final String MESSAGE = "Academic program not found";

    public AcademicProgramNotFoundException() {
        super(MESSAGE);
    }

    public AcademicProgramNotFoundException(Long id) {
        super(MESSAGE + " with id: " + id);
    }
}
