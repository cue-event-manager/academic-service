package cue.edu.co.jpa.repositories;

import cue.edu.co.jpa.entities.FacultyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FacultyJpaRepository  extends JpaRepository<FacultyEntity, Long>, JpaSpecificationExecutor<FacultyEntity> {
}
