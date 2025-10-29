package cue.edu.co.usecase.academicarea;

import cue.edu.co.model.academicarea.gateways.AcademicAreaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistsAcademicAreaUseCase {
    private final AcademicAreaRepository academicAreaRepository;

    public Boolean execute(Long id){
        return academicAreaRepository.existsById(id);
    }
}
