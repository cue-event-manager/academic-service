package cue.edu.co.seeder.seeders;

import cue.edu.co.model.academicprogram.AcademicProgram;
import cue.edu.co.model.faculty.Faculty;
import cue.edu.co.model.faculty.gateways.FacultyRepository;
import cue.edu.co.model.academicprogram.gateways.AcademicProgramRepository;
import cue.edu.co.seeder.constants.AcademicProgramSeedConstant;
import cue.edu.co.seeder.constants.AcademicProgramSeederLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(3)
public class AcademicProgramSeeder implements CommandLineRunner {

    private final AcademicProgramRepository programRepository;
    private final FacultyRepository facultyRepository;

    @Override
    public void run(String... args) {
        log.info(AcademicProgramSeederLog.STARTING.getMessage());

        for (AcademicProgramSeedConstant seed : AcademicProgramSeedConstant.values()) {
            Optional<Faculty> facultyOpt = facultyRepository.findByName(seed.getFacultyName());
            if (facultyOpt.isEmpty()) {
                log.warn(AcademicProgramSeederLog.FACULTY_NOT_FOUND.getMessage(), seed.getName());
                continue;
            }

            Faculty faculty = facultyOpt.get();

            programRepository.findByName(seed.getName())
                    .ifPresentOrElse(
                            p -> log.info(AcademicProgramSeederLog.PROGRAM_FOUND.getMessage(), seed.getName()),
                            () -> {
                                AcademicProgram program = AcademicProgram.builder()
                                        .name(seed.getName())
                                        .description(seed.getDescription())
                                        .faculty(faculty)
                                        .build();

                                programRepository.save(program);
                                log.info(AcademicProgramSeederLog.PROGRAM_CREATED.getMessage(), seed.getName());
                            }
                    );
        }

        log.info(AcademicProgramSeederLog.FINISHED.getMessage());
    }
}
