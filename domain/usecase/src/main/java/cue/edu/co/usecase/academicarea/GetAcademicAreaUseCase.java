package cue.edu.co.usecase.academicarea;

import cue.edu.co.model.academicarea.AcademicArea;
import cue.edu.co.model.academicarea.GetAcademicAreaQuery;
import cue.edu.co.model.academicarea.exceptions.AcademicAreaNotFoundException;
import cue.edu.co.model.academicarea.gateways.AcademicAreaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetAcademicAreaUseCase {
    private final AcademicAreaRepository academicAreaRepository;

    public AcademicArea execute(GetAcademicAreaQuery query) {
        return academicAreaRepository.findById(query.id())
                .orElseThrow(() -> new AcademicAreaNotFoundException(query.id()));
    }
}
