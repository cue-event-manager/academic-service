package cue.edu.co.jpa.adapters;

import cue.edu.co.jpa.mappers.FacultyMapper;
import cue.edu.co.jpa.repositories.FacultyJpaRepository;
import cue.edu.co.model.faculty.Faculty;
import cue.edu.co.model.faculty.gateways.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FacultyRepositoryAdapter implements FacultyRepository {
    private final FacultyJpaRepository facultyJpaRepository;
    private final FacultyMapper facultyMapper;

    @Override
    public Optional<Faculty> findById(Long id) {
        return facultyJpaRepository.findById(id)
                .map(facultyMapper::toDomain);
    }
}
