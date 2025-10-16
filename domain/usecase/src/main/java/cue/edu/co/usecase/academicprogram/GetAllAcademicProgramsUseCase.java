package cue.edu.co.usecase.academicprogram;

import cue.edu.co.model.academicprogram.AcademicProgram;
import cue.edu.co.model.academicprogram.gateways.AcademicProgramRepository;
import cue.edu.co.model.academicprogram.queries.AcademicProgramPaginationQuery;
import cue.edu.co.model.common.results.PageResult;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetAllAcademicProgramsUseCase {
    private final AcademicProgramRepository academicProgramRepository;

    public PageResult<AcademicProgram> execute(AcademicProgramPaginationQuery query) {
        return academicProgramRepository.findAllByFilters(query);
    }
}
