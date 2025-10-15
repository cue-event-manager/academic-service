package cue.edu.co.usecase.academicarea;

import cue.edu.co.model.academicarea.commands.DeleteAcademicAreaCommand;
import cue.edu.co.model.academicarea.exceptions.AcademicAreaNotFoundException;
import cue.edu.co.model.academicarea.gateways.AcademicAreaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteAcademicAreaUseCase {
    private final AcademicAreaRepository academicAreaRepository;

    public void execute(DeleteAcademicAreaCommand command) {
        if (!academicAreaRepository.findById(command.id()).isPresent()) {
            throw new AcademicAreaNotFoundException(command.id());
        }

        academicAreaRepository.deleteById(command.id());
    }
}
