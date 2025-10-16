package cue.edu.co.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SoftDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static cue.edu.co.jpa.constants.AcademicProgramColumn.CREATED_AT;
import static cue.edu.co.jpa.constants.AcademicProgramColumn.FACULTY_ID;
import static cue.edu.co.jpa.constants.TableConstant.ACADEMIC_PROGRAM_TABLE;
import static cue.edu.co.model.academicprogram.constants.AcademicProgramConstant.MAX_NAME_LENGTH;
import static cue.edu.co.model.academicprogram.constants.AcademicProgramConstant.MAX_DESCRIPTION_LENGTH;

@Data
@Entity
@SoftDelete
@Table(name = ACADEMIC_PROGRAM_TABLE)
@EntityListeners(AuditingEntityListener.class)
public class AcademicProgramEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = MAX_NAME_LENGTH)
    private String name;

    @Column(length = MAX_DESCRIPTION_LENGTH)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = FACULTY_ID, nullable = false)
    private FacultyEntity faculty;

    @Column(name = CREATED_AT, nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;
}
