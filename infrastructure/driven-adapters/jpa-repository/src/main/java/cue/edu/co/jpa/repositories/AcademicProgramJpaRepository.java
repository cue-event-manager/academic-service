package cue.edu.co.jpa.repositories;

import cue.edu.co.jpa.entities.AcademicProgramEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface AcademicProgramJpaRepository extends JpaRepository<AcademicProgramEntity, Long>, JpaSpecificationExecutor<AcademicProgramEntity> {
    Optional<AcademicProgramEntity> findByName(String name);
}
