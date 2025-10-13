package cue.edu.co.model.academicarea;
import lombok.*;

import java.time.LocalDateTime;


@Data
@Builder(toBuilder = true)
public class AcademicArea {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
}
