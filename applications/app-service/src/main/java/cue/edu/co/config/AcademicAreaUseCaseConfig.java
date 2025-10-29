package cue.edu.co.config;

import cue.edu.co.model.academicarea.gateways.AcademicAreaRepository;
import cue.edu.co.usecase.academicarea.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AcademicAreaUseCaseConfig {

    @Bean
    public CreateAcademicAreaUseCase createAcademicAreaUseCase(AcademicAreaRepository academicAreaRepository) {
        return new CreateAcademicAreaUseCase(academicAreaRepository);
    }

    @Bean
    public UpdateAcademicAreaUseCase updateAcademicAreaUseCase(AcademicAreaRepository academicAreaRepository) {
        return new UpdateAcademicAreaUseCase(academicAreaRepository);
    }

    @Bean
    public DeleteAcademicAreaUseCase deleteAcademicAreaUseCase(AcademicAreaRepository academicAreaRepository) {
        return new DeleteAcademicAreaUseCase(academicAreaRepository);
    }

    @Bean
    public GetAcademicAreaUseCase getAcademicAreaUseCase(AcademicAreaRepository academicAreaRepository) {
        return new GetAcademicAreaUseCase(academicAreaRepository);
    }

    @Bean
    public GetAllAcademicAreasUseCase getAllAcademicAreasUseCase(AcademicAreaRepository academicAreaRepository) {
        return new GetAllAcademicAreasUseCase(academicAreaRepository);
    }

    @Bean
    public ExistsAcademicAreaUseCase existsAcademicAreaUseCase(AcademicAreaRepository academicAreaRepository){
        return new ExistsAcademicAreaUseCase(academicAreaRepository);
    }
}
