package cue.edu.co.model.faculty.exceptions;

import cue.edu.co.model.common.NotFoundException;

public class FacultyNotFoundException extends NotFoundException {
    private static final String MESSAGE = "Faculty not found";

    public FacultyNotFoundException() {
        super(MESSAGE);
    }

    public FacultyNotFoundException(Long id) {
        super(MESSAGE + " with id: " + id);
    }
}
