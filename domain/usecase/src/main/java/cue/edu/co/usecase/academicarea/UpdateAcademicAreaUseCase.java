package cue.edu.co.usecase.academicarea;

import cue.edu.co.model.academicarea.AcademicArea;
import cue.edu.co.model.academicarea.commands.UpdateAcademicAreaCommand;
import cue.edu.co.model.academicarea.exceptions.AcademicAreaNotFoundException;
import cue.edu.co.model.academicarea.exceptions.DuplicateAcademicAreaNameException;
import cue.edu.co.model.academicarea.gateways.AcademicAreaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateAcademicAreaUseCase {
    private final AcademicAreaRepository academicAreaRepository;

    public AcademicArea execute(UpdateAcademicAreaCommand command) {
        AcademicArea existing = academicAreaRepository.findById(command.id())
                .orElseThrow(() -> new AcademicAreaNotFoundException(command.id()));

        if (academicAreaRepository.existsByNameAndIdNot(command.name(), command.id())) {
            throw new DuplicateAcademicAreaNameException(command.name());
        }

        AcademicArea updated = command.toDomain(existing);
        return academicAreaRepository.save(updated);
    }
}
