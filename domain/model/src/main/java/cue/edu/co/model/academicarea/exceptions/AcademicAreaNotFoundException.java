package cue.edu.co.model.academicarea.exceptions;

import cue.edu.co.model.common.NotFoundException;

public class AcademicAreaNotFoundException extends NotFoundException {
    private static final String MESSAGE = "Academic area not found";

    public AcademicAreaNotFoundException() {
        super(MESSAGE);
    }

    public AcademicAreaNotFoundException(Long id) {
        super(MESSAGE + " with id: " + id);
    }
}
