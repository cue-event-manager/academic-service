package cue.edu.co.usecase.faculty;

import cue.edu.co.model.faculty.Faculty;
import cue.edu.co.model.faculty.commands.UpdateFacultyCommand;
import cue.edu.co.model.faculty.exceptions.DuplicateFacultyNameException;
import cue.edu.co.model.faculty.exceptions.FacultyNotFoundException;
import cue.edu.co.model.faculty.gateways.FacultyRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateFacultyUseCase {
    private final FacultyRepository facultyRepository;

    public Faculty execute(UpdateFacultyCommand command) {
        Faculty existing = facultyRepository.findById(command.id())
                .orElseThrow(() -> new FacultyNotFoundException(command.id()));

        if (facultyRepository.existsByNameAndIdNot(command.name(), command.id())) {
            throw new DuplicateFacultyNameException(command.name());
        }

        Faculty updatedFaculty = command.toDomain(existing);
        return facultyRepository.save(updatedFaculty);
    }
}
