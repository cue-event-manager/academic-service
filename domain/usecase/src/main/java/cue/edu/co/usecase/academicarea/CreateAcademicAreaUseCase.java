package cue.edu.co.usecase.academicarea;

import cue.edu.co.model.academicarea.AcademicArea;
import cue.edu.co.model.academicarea.commands.CreateAcademicAreaCommand;
import cue.edu.co.model.academicarea.exceptions.DuplicateAcademicAreaNameException;
import cue.edu.co.model.academicarea.gateways.AcademicAreaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateAcademicAreaUseCase {
    private final AcademicAreaRepository academicAreaRepository;

    public AcademicArea execute(CreateAcademicAreaCommand command) {
        if (academicAreaRepository.existsByName(command.name())) {
            throw new DuplicateAcademicAreaNameException(command.name());
        }

        AcademicArea academicArea = command.toDomain();
        return academicAreaRepository.save(academicArea);
    }
}
