package cue.edu.co.jpa.adapters;

import cue.edu.co.jpa.entities.AcademicProgramEntity;
import cue.edu.co.jpa.mappers.AcademicProgramMapper;
import cue.edu.co.jpa.mappers.PaginationMapper;
import cue.edu.co.jpa.repositories.AcademicProgramJpaRepository;
import cue.edu.co.jpa.specifications.AcademicProgramSpecificationBuilder;
import cue.edu.co.model.academicprogram.AcademicProgram;
import cue.edu.co.model.academicprogram.gateways.AcademicProgramRepository;
import cue.edu.co.model.academicprogram.queries.AcademicProgramPaginationQuery;
import cue.edu.co.model.common.results.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AcademicProgramRepositoryAdapter implements AcademicProgramRepository {
    private final AcademicProgramJpaRepository academicProgramJpaRepository;
    private final AcademicProgramMapper academicProgramMapper;
    private final PaginationMapper paginationMapper;

    @Override
    public AcademicProgram save(AcademicProgram academicProgram) {
        AcademicProgramEntity entity = academicProgramMapper.toEntity(academicProgram);
        AcademicProgramEntity savedEntity = academicProgramJpaRepository.save(entity);
        return academicProgramMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<AcademicProgram> findById(Long id) {
        return academicProgramJpaRepository.findById(id)
                .map(academicProgramMapper::toDomain);
    }

    @Override
    public Optional<AcademicProgram> findByName(String name) {
        return academicProgramJpaRepository
                .findByName(name)
                .map(academicProgramMapper::toDomain);
    }

    @Override
    public boolean existsById(Long id) {
        return academicProgramJpaRepository.existsById(id);
    }

    @Override
    public boolean existsByNameAndFacultyId(String name, Long facultyId) {
        return academicProgramJpaRepository.exists((root, query, cb) ->
                cb.and(
                        cb.equal(cb.lower(root.get("name")), name.toLowerCase()),
                        cb.equal(root.get("faculty").get("id"), facultyId)
                )
        );
    }

    @Override
    public boolean existsByNameAndFacultyIdAndIdNot(String name, Long facultyId, Long id) {
        return academicProgramJpaRepository.exists((root, query, cb) ->
                cb.and(
                        cb.equal(cb.lower(root.get("name")), name.toLowerCase()),
                        cb.equal(root.get("faculty").get("id"), facultyId),
                        cb.notEqual(root.get("id"), id)
                )
        );
    }

    @Override
    public void deleteById(Long id) {
        academicProgramJpaRepository.deleteById(id);
    }

    @Override
    public PageResult<AcademicProgram> findAllByFilters(AcademicProgramPaginationQuery query) {
        Pageable pageable = paginationMapper.toPageable(query.pagination());
        Specification<AcademicProgramEntity> spec = AcademicProgramSpecificationBuilder.build(query);
        Page<AcademicProgramEntity> page = academicProgramJpaRepository.findAll(spec, pageable);
        return paginationMapper.toPageResult(page, academicProgramMapper::toDomain);
    }

    @Override
    public List<AcademicProgram> findAll() {
        return academicProgramJpaRepository
                .findAll()
                .stream()
                .map(academicProgramMapper::toDomain)
                .toList();
    }
}
