package cue.edu.co.jpa.mappers;

import cue.edu.co.jpa.entities.FacultyEntity;
import cue.edu.co.model.faculty.Faculty;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FacultyMapper {
    Faculty toDomain(FacultyEntity entity);
    FacultyEntity toEntity(Faculty domain);
}
