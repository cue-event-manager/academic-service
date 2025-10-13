package cue.edu.co.model.academicprogram;
import cue.edu.co.model.faculty.Faculty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class AcademicProgram {
    private Long id;
    private String name;
    private String description;
    private Faculty faculty;
    private LocalDateTime createdAt;
}
