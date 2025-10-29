package cue.edu.co.usecase.academicprogram;

import cue.edu.co.model.academicprogram.gateways.AcademicProgramRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistsAcademicProgramUseCase {
    private final AcademicProgramRepository academicProgramRepository;

    public Boolean execute(Long id){
        return academicProgramRepository.existsById(id);
    }
}
