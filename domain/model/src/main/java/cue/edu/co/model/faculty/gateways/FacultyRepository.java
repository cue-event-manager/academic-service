package cue.edu.co.model.faculty.gateways;

import cue.edu.co.model.faculty.Faculty;

import java.util.Optional;

public interface FacultyRepository {
    Optional<Faculty> findById(Long id);
}
