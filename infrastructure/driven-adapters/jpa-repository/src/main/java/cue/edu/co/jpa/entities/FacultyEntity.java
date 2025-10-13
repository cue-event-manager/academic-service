package cue.edu.co.jpa.entities;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SoftDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static cue.edu.co.jpa.constants.FacultyColumn.CREATED_AT;
import static cue.edu.co.jpa.constants.TableConstant.FACULTY_TABLE;
import static cue.edu.co.model.faculty.constants.FacultyConstant.MAX_DESCRIPTION_LENGTH;
import static cue.edu.co.model.faculty.constants.FacultyConstant.MAX_NAME_LENGTH;

@Data
@Entity
@SoftDelete
@Table(name = FACULTY_TABLE)
@EntityListeners(AuditingEntityListener.class)
public class FacultyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = MAX_NAME_LENGTH, unique = true)
    private String name;

    @Column(length = MAX_DESCRIPTION_LENGTH)
    private String description;

    @Column(name = CREATED_AT, nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;
}
