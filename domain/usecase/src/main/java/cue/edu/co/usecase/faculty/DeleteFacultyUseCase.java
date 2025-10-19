package cue.edu.co.usecase.faculty;

import cue.edu.co.model.faculty.commands.DeleteFacultyCommand;
import cue.edu.co.model.faculty.exceptions.FacultyHasAssociatedProgramsException;
import cue.edu.co.model.faculty.exceptions.FacultyNotFoundException;
import cue.edu.co.model.faculty.gateways.FacultyRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteFacultyUseCase {
    private final FacultyRepository facultyRepository;

    public void execute(DeleteFacultyCommand command) {
        if (!facultyRepository.findById(command.id()).isPresent()) {
            throw new FacultyNotFoundException(command.id());
        }

        if (facultyRepository.hasActiveAcademicPrograms(command.id())) {
            throw new FacultyHasAssociatedProgramsException(command.id());
        }

        facultyRepository.deleteById(command.id());
    }
}
