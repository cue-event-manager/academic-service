package cue.edu.co.usecase.academicprogram;

import cue.edu.co.model.academicprogram.commands.DeleteAcademicProgramCommand;
import cue.edu.co.model.academicprogram.exceptions.AcademicProgramNotFoundException;
import cue.edu.co.model.academicprogram.gateways.AcademicProgramRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteAcademicProgramUseCase {
    private final AcademicProgramRepository academicProgramRepository;

    public void execute(DeleteAcademicProgramCommand command) {
        if (!academicProgramRepository.findById(command.id()).isPresent()) {
            throw new AcademicProgramNotFoundException(command.id());
        }

        academicProgramRepository.deleteById(command.id());
    }
}
