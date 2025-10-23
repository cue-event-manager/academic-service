package cue.edu.co.seeder.seeders;

import cue.edu.co.model.academicarea.AcademicArea;
import cue.edu.co.model.academicarea.gateways.AcademicAreaRepository;
import cue.edu.co.seeder.constants.AcademicAreaSeedConstant;
import cue.edu.co.seeder.constants.AcademicAreaSeederLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(4)
public class AcademicAreaSeeder implements CommandLineRunner {

    private final AcademicAreaRepository academicAreaRepository;

    @Override
    public void run(String... args) {
        log.info(AcademicAreaSeederLog.STARTING.getMessage());

        for (AcademicAreaSeedConstant seed : AcademicAreaSeedConstant.values()) {
            academicAreaRepository.findByName(seed.getName())
                    .ifPresentOrElse(
                            a -> log.info(AcademicAreaSeederLog.AREA_FOUND.getMessage(), seed.getName()),
                            () -> {
                                AcademicArea area = AcademicArea.builder()
                                        .name(seed.getName())
                                        .description(seed.getDescription())
                                        .build();

                                academicAreaRepository.save(area);
                                log.info(AcademicAreaSeederLog.AREA_CREATED.getMessage(), seed.getName());
                            }
                    );
        }

        log.info(AcademicAreaSeederLog.FINISHED.getMessage());
    }
}