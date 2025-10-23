package cue.edu.co.seeder.seeders;

import cue.edu.co.model.faculty.Faculty;
import cue.edu.co.model.faculty.gateways.FacultyRepository;
import cue.edu.co.seeder.constants.FacultySeedConstant;
import cue.edu.co.seeder.constants.FacultySeederLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(2)
public class FacultySeeder implements CommandLineRunner {

    private final FacultyRepository facultyRepository;

    @Override
    public void run(String... args) {
        log.info(FacultySeederLog.STARTING.getMessage());

        for (FacultySeedConstant seed : FacultySeedConstant.values()) {
            facultyRepository.findByName(seed.getName())
                    .ifPresentOrElse(
                            f -> log.info(FacultySeederLog.FACULTY_FOUND.getMessage(), seed.getName()),
                            () -> {
                                Faculty newFaculty = Faculty.builder()
                                        .name(seed.getName())
                                        .description(seed.getDescription())
                                        .build();

                                facultyRepository.save(newFaculty);
                                log.info(FacultySeederLog.FACULTY_CREATED.getMessage(), seed.getName());
                            }
                    );
        }

        log.info(FacultySeederLog.FINISHED.getMessage());
    }
}