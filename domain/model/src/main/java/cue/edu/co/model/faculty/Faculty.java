package cue.edu.co.model.faculty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class Faculty {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
}
