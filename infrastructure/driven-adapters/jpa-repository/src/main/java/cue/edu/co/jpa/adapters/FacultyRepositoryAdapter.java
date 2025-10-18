package cue.edu.co.jpa.adapters;

import cue.edu.co.jpa.entities.FacultyEntity;
import cue.edu.co.jpa.mappers.FacultyMapper;
import cue.edu.co.jpa.mappers.PaginationMapper;
import cue.edu.co.jpa.repositories.FacultyJpaRepository;
import cue.edu.co.jpa.specifications.FacultySpecificationBuilder;
import cue.edu.co.model.common.results.PageResult;
import cue.edu.co.model.faculty.Faculty;
import cue.edu.co.model.faculty.gateways.FacultyRepository;
import cue.edu.co.model.faculty.queries.FacultyPaginationQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FacultyRepositoryAdapter implements FacultyRepository {
    private final FacultyJpaRepository facultyJpaRepository;
    private final FacultyMapper facultyMapper;
    private final PaginationMapper paginationMapper;

    @Override
    public Faculty save(Faculty faculty) {
        FacultyEntity entity = facultyMapper.toEntity(faculty);
        FacultyEntity savedEntity = facultyJpaRepository.save(entity);
        return facultyMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Faculty> findById(Long id) {
        return facultyJpaRepository.findById(id)
                .map(facultyMapper::toDomain);
    }

    @Override
    public List<Faculty> findAll() {
        return facultyJpaRepository.findAll()
                .stream()
                .map(facultyMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByName(String name) {
        return facultyJpaRepository.existsByName(name);
    }

    @Override
    public boolean existsByNameAndIdNot(String name, Long id) {
        return facultyJpaRepository.existsByNameAndIdNot(name, id);
    }

    @Override
    public PageResult<Faculty> findAllByFilters(FacultyPaginationQuery query) {
        Pageable pageable = paginationMapper.toPageable(query.pagination());
        Specification<FacultyEntity> spec = FacultySpecificationBuilder.build(query);
        Page<FacultyEntity> page = facultyJpaRepository.findAll(spec, pageable);
        return paginationMapper.toPageResult(page, facultyMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        facultyJpaRepository.deleteById(id);
    }

    @Override
    public boolean hasActiveAcademicPrograms(Long facultyId) {
        return facultyJpaRepository.hasActiveAcademicPrograms(facultyId);
    }
}
