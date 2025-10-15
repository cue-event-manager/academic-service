package cue.edu.co.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SoftDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static cue.edu.co.jpa.constants.AcademicAreaColumn.CREATED_AT;
import static cue.edu.co.jpa.constants.TableConstant.ACADEMIC_AREA_TABLE;
import static cue.edu.co.model.academicarea.constants.AcademicAreaConstant.NAME_MAX_SIZE;
import static cue.edu.co.model.academicarea.constants.AcademicAreaConstant.DESCRIPTION_MAX_SIZE;

@Data
@Entity
@SoftDelete
@Table(name = ACADEMIC_AREA_TABLE)
@EntityListeners(AuditingEntityListener.class)
public class AcademicAreaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = NAME_MAX_SIZE, unique = true)
    private String name;

    @Column(length = DESCRIPTION_MAX_SIZE)
    private String description;

    @Column(name = CREATED_AT, nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;
}
