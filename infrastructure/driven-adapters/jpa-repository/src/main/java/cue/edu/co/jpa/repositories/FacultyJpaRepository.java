package cue.edu.co.jpa.repositories;

import cue.edu.co.jpa.entities.FacultyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FacultyJpaRepository extends JpaRepository<FacultyEntity, Long>, JpaSpecificationExecutor<FacultyEntity> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);

    Optional<FacultyEntity> findByName(String name);

    @Query("SELECT COUNT(ap) > 0 FROM AcademicProgramEntity ap WHERE ap.faculty.id = :facultyId")
    boolean hasActiveAcademicPrograms(@Param("facultyId") Long facultyId);
}
