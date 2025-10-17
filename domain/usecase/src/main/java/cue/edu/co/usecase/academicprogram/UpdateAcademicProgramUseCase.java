package cue.edu.co.usecase.academicprogram;

import cue.edu.co.model.academicprogram.AcademicProgram;
import cue.edu.co.model.academicprogram.commands.UpdateAcademicProgramCommand;
import cue.edu.co.model.academicprogram.exceptions.AcademicProgramNotFoundException;
import cue.edu.co.model.academicprogram.exceptions.DuplicateAcademicProgramNameException;
import cue.edu.co.model.academicprogram.gateways.AcademicProgramRepository;
import cue.edu.co.model.faculty.Faculty;
import cue.edu.co.model.faculty.exceptions.FacultyNotFoundException;
import cue.edu.co.model.faculty.gateways.FacultyRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateAcademicProgramUseCase {
    private final AcademicProgramRepository academicProgramRepository;
    private final FacultyRepository facultyRepository;

    public AcademicProgram execute(UpdateAcademicProgramCommand command) {
        AcademicProgram existing = academicProgramRepository.findById(command.id())
                .orElseThrow(() -> new AcademicProgramNotFoundException(command.id()));

        Faculty faculty = facultyRepository.findById(command.facultyId())
                .orElseThrow(() -> new FacultyNotFoundException(command.facultyId()));

        if (academicProgramRepository.existsByNameAndFacultyIdAndIdNot(command.name(), command.facultyId(), command.id())) {
            throw new DuplicateAcademicProgramNameException(command.name());
        }

        AcademicProgram updated = AcademicProgram.builder()
                .id(existing.getId())
                .name(command.name())
                .description(command.description())
                .faculty(faculty)
                .createdAt(existing.getCreatedAt())
                .build();

        return academicProgramRepository.save(updated);
    }
}
