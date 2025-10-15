package cue.edu.co.jpa.mappers;

import cue.edu.co.jpa.entities.AcademicAreaEntity;
import cue.edu.co.model.academicarea.AcademicArea;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AcademicAreaMapper {
    AcademicArea toDomain(AcademicAreaEntity entity);
    AcademicAreaEntity toEntity(AcademicArea domain);
}
