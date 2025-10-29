package cue.edu.co.usecase.faculty;

import cue.edu.co.model.faculty.gateways.FacultyRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistsFacultyUseCase {
    private final FacultyRepository facultyRepository;

    public Boolean execute(Long id){
        return facultyRepository.existsById(id);
    }
}
