package cue.edu.co.jpa.mappers;

import cue.edu.co.jpa.entities.AcademicProgramEntity;
import cue.edu.co.model.academicprogram.AcademicProgram;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {FacultyMapper.class})
public interface AcademicProgramMapper {
    AcademicProgram toDomain(AcademicProgramEntity entity);
    AcademicProgramEntity toEntity(AcademicProgram domain);
}
