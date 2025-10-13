package cue.edu.co.jpa.repositories;

import cue.edu.co.jpa.entities.AcademicAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AcademicAreaJpaRepository extends JpaRepository<AcademicAreaEntity, Long>, JpaSpecificationExecutor<AcademicAreaEntity> {
}
