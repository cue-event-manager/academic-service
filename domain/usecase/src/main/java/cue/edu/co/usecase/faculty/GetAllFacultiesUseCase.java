package cue.edu.co.usecase.faculty;

import cue.edu.co.model.common.results.PageResult;
import cue.edu.co.model.faculty.Faculty;
import cue.edu.co.model.faculty.gateways.FacultyRepository;
import cue.edu.co.model.faculty.queries.FacultyPaginationQuery;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetAllFacultiesUseCase {
    private final FacultyRepository facultyRepository;

    public PageResult<Faculty> execute(FacultyPaginationQuery query) {
        return facultyRepository.findAllByFilters(query);
    }
}
