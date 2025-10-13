package cue.edu.co.jpa.repositories;

import cue.edu.co.jpa.entities.AcademicProgramEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AcademicProgramJpaRepository extends JpaRepository<AcademicProgramEntity, Long>, JpaSpecificationExecutor<AcademicProgramEntity> {
}
