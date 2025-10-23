package cue.edu.co.jpa.adapters;

import cue.edu.co.jpa.entities.AcademicAreaEntity;
import cue.edu.co.jpa.mappers.AcademicAreaMapper;
import cue.edu.co.jpa.mappers.PaginationMapper;
import cue.edu.co.jpa.repositories.AcademicAreaJpaRepository;
import cue.edu.co.jpa.specifications.AcademicAreaSpecificationBuilder;
import cue.edu.co.model.academicarea.AcademicArea;
import cue.edu.co.model.academicarea.gateways.AcademicAreaRepository;
import cue.edu.co.model.academicarea.queries.AcademicAreaPaginationQuery;
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
public class AcademicAreaRepositoryAdapter implements AcademicAreaRepository {
    private final AcademicAreaJpaRepository academicAreaJpaRepository;
    private final AcademicAreaMapper academicAreaMapper;
    private final PaginationMapper paginationMapper;

    @Override
    public AcademicArea save(AcademicArea academicArea) {
        AcademicAreaEntity entity = academicAreaMapper.toEntity(academicArea);
        AcademicAreaEntity savedEntity = academicAreaJpaRepository.save(entity);
        return academicAreaMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<AcademicArea> findById(Long id) {
        return academicAreaJpaRepository.findById(id)
                .map(academicAreaMapper::toDomain);
    }

    @Override
    public Optional<AcademicArea> findByName(String name) {
        return academicAreaJpaRepository
                .findByName(name)
                .map(academicAreaMapper::toDomain);
    }

    @Override
    public List<AcademicArea> findAll() {
        return academicAreaJpaRepository
                .findAll()
                .stream()
                .map(academicAreaMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByName(String name) {
        return academicAreaJpaRepository.existsByName(name);
    }

    @Override
    public boolean existsByNameAndIdNot(String name, Long id) {
        return academicAreaJpaRepository.existsByNameAndIdNot(name, id);
    }

    @Override
    public void deleteById(Long id) {
        academicAreaJpaRepository.deleteById(id);
    }

    @Override
    public PageResult<AcademicArea> findAllByFilters(AcademicAreaPaginationQuery query) {
        Pageable pageable = paginationMapper.toPageable(query.pagination());
        Specification<AcademicAreaEntity> spec = AcademicAreaSpecificationBuilder.build(query);
        Page<AcademicAreaEntity> page = academicAreaJpaRepository.findAll(spec, pageable);
        return paginationMapper.toPageResult(page, academicAreaMapper::toDomain);
    }
}
