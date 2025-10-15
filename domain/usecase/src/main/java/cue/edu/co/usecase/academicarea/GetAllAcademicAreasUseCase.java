package cue.edu.co.usecase.academicarea;

import cue.edu.co.model.academicarea.AcademicArea;
import cue.edu.co.model.academicarea.gateways.AcademicAreaRepository;
import cue.edu.co.model.academicarea.queries.AcademicAreaPaginationQuery;
import cue.edu.co.model.common.results.PageResult;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetAllAcademicAreasUseCase {
    private final AcademicAreaRepository academicAreaRepository;

    public PageResult<AcademicArea> execute(AcademicAreaPaginationQuery query) {
        return academicAreaRepository.findAllByFilters(query);
    }
}
