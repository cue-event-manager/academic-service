package cue.edu.co.usecase.academicprogram;

import cue.edu.co.model.academicprogram.AcademicProgram;
import cue.edu.co.model.academicprogram.queries.GetAcademicProgramQuery;
import cue.edu.co.model.academicprogram.exceptions.AcademicProgramNotFoundException;
import cue.edu.co.model.academicprogram.gateways.AcademicProgramRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetAcademicProgramUseCase {
    private final AcademicProgramRepository academicProgramRepository;

    public AcademicProgram execute(GetAcademicProgramQuery query) {
        return academicProgramRepository.findById(query.id())
                .orElseThrow(() -> new AcademicProgramNotFoundException(query.id()));
    }
}
