package cue.edu.co.config;

import cue.edu.co.model.faculty.gateways.FacultyRepository;
import cue.edu.co.usecase.faculty.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FacultyUseCaseConfig {

    @Bean
    public CreateFacultyUseCase createFacultyUseCase(FacultyRepository facultyRepository) {
        return new CreateFacultyUseCase(facultyRepository);
    }

    @Bean
    public UpdateFacultyUseCase updateFacultyUseCase(FacultyRepository facultyRepository) {
        return new UpdateFacultyUseCase(facultyRepository);
    }

    @Bean
    public DeleteFacultyUseCase deleteFacultyUseCase(FacultyRepository facultyRepository) {
        return new DeleteFacultyUseCase(facultyRepository);
    }

    @Bean
    public GetFacultyUseCase getFacultyUseCase(FacultyRepository facultyRepository) {
        return new GetFacultyUseCase(facultyRepository);
    }

    @Bean
    public GetAllFacultiesUseCase getAllFacultiesUseCase(FacultyRepository facultyRepository) {
        return new GetAllFacultiesUseCase(facultyRepository);
    }
}
