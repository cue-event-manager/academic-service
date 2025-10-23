package cue.edu.co.jpa.repositories;

import cue.edu.co.jpa.entities.AcademicAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface AcademicAreaJpaRepository extends JpaRepository<AcademicAreaEntity, Long>, JpaSpecificationExecutor<AcademicAreaEntity> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
    Optional<AcademicAreaEntity> findByName(String name);
}
