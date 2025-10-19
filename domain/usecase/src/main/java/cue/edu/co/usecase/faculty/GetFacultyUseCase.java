package cue.edu.co.usecase.faculty;

import cue.edu.co.model.faculty.Faculty;
import cue.edu.co.model.faculty.GetFacultyQuery;
import cue.edu.co.model.faculty.exceptions.FacultyNotFoundException;
import cue.edu.co.model.faculty.gateways.FacultyRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetFacultyUseCase {
    private final FacultyRepository facultyRepository;

    public Faculty execute(GetFacultyQuery query) {
        return facultyRepository.findById(query.id())
                .orElseThrow(() -> new FacultyNotFoundException(query.id()));
    }
}
