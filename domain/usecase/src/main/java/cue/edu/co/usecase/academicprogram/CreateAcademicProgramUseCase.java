package cue.edu.co.usecase.academicprogram;

import cue.edu.co.model.academicprogram.AcademicProgram;
import cue.edu.co.model.academicprogram.commands.CreateAcademicProgramCommand;
import cue.edu.co.model.academicprogram.exceptions.DuplicateAcademicProgramNameException;
import cue.edu.co.model.academicprogram.gateways.AcademicProgramRepository;
import cue.edu.co.model.faculty.Faculty;
import cue.edu.co.model.faculty.exceptions.FacultyNotFoundException;
import cue.edu.co.model.faculty.gateways.FacultyRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class CreateAcademicProgramUseCase {
    private final AcademicProgramRepository academicProgramRepository;
    private final FacultyRepository facultyRepository;

    public AcademicProgram execute(CreateAcademicProgramCommand command) {
        Faculty faculty = facultyRepository.findById(command.facultyId())
                .orElseThrow(() -> new FacultyNotFoundException(command.facultyId()));

        if (academicProgramRepository.existsByNameAndFacultyId(command.name(), command.facultyId())) {
            throw new DuplicateAcademicProgramNameException(command.name());
        }

        AcademicProgram academicProgram = AcademicProgram.builder()
                .name(command.name())
                .description(command.description())
                .faculty(faculty)
                .createdAt(LocalDateTime.now())
                .build();

        return academicProgramRepository.save(academicProgram);
    }
}
