package cue.edu.co.usecase.faculty;

import cue.edu.co.model.faculty.Faculty;
import cue.edu.co.model.faculty.commands.CreateFacultyCommand;
import cue.edu.co.model.faculty.exceptions.DuplicateFacultyNameException;
import cue.edu.co.model.faculty.gateways.FacultyRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateFacultyUseCase {
    private final FacultyRepository facultyRepository;

    public Faculty execute(CreateFacultyCommand command) {
        if (facultyRepository.existsByName(command.name())) {
            throw new DuplicateFacultyNameException(command.name());
        }

        Faculty faculty = command.toDomain();
        return facultyRepository.save(faculty);
    }
}
