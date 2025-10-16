package cue.edu.co.config;

import cue.edu.co.model.academicprogram.gateways.AcademicProgramRepository;
import cue.edu.co.model.faculty.gateways.FacultyRepository;
import cue.edu.co.usecase.academicprogram.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AcademicProgramUseCaseConfig {

    @Bean
    public CreateAcademicProgramUseCase createAcademicProgramUseCase(
            AcademicProgramRepository academicProgramRepository,
            FacultyRepository facultyRepository) {
        return new CreateAcademicProgramUseCase(academicProgramRepository, facultyRepository);
    }

    @Bean
    public UpdateAcademicProgramUseCase updateAcademicProgramUseCase(
            AcademicProgramRepository academicProgramRepository,
            FacultyRepository facultyRepository) {
        return new UpdateAcademicProgramUseCase(academicProgramRepository, facultyRepository);
    }

    @Bean
    public DeleteAcademicProgramUseCase deleteAcademicProgramUseCase(AcademicProgramRepository academicProgramRepository) {
        return new DeleteAcademicProgramUseCase(academicProgramRepository);
    }

    @Bean
    public GetAcademicProgramUseCase getAcademicProgramUseCase(AcademicProgramRepository academicProgramRepository) {
        return new GetAcademicProgramUseCase(academicProgramRepository);
    }

    @Bean
    public GetAllAcademicProgramsUseCase getAllAcademicProgramsUseCase(AcademicProgramRepository academicProgramRepository) {
        return new GetAllAcademicProgramsUseCase(academicProgramRepository);
    }
}
