package cue.edu.co.api.academicprogram.mappers;

import cue.edu.co.api.academicprogram.dtos.AcademicProgramPaginationRequestDto;
import cue.edu.co.api.academicprogram.dtos.AcademicProgramResponseDto;
import cue.edu.co.api.academicprogram.dtos.CreateAcademicProgramRequestDto;
import cue.edu.co.api.academicprogram.dtos.UpdateAcademicProgramRequestDto;
import cue.edu.co.api.common.dtos.PaginationRequestDto;
import cue.edu.co.api.common.dtos.PaginationResponseDto;
import cue.edu.co.api.common.mappers.OptionalMapper;
import cue.edu.co.api.common.mappers.PaginationDtoMapper;
import cue.edu.co.model.academicprogram.AcademicProgram;
import cue.edu.co.model.academicprogram.commands.CreateAcademicProgramCommand;
import cue.edu.co.model.academicprogram.commands.UpdateAcademicProgramCommand;
import cue.edu.co.model.academicprogram.queries.AcademicProgramPaginationQuery;
import cue.edu.co.model.common.results.PageResult;
import cue.edu.co.model.faculty.Faculty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { OptionalMapper.class, PaginationDtoMapper.class })
public interface AcademicProgramDtoMapper {

    CreateAcademicProgramCommand toCommand(CreateAcademicProgramRequestDto dto);

    @Mapping(target = "id", source = "id")
    UpdateAcademicProgramCommand toCommand(Long id, UpdateAcademicProgramRequestDto dto);

    AcademicProgramResponseDto toDto(AcademicProgram academicProgram);

    @Mapping(target = "pagination", source = "paginationRequestDto")
    AcademicProgramPaginationQuery toQuery(
            AcademicProgramPaginationRequestDto academicProgramPaginationRequestDto,
            PaginationRequestDto paginationRequestDto
    );

    PaginationResponseDto<AcademicProgramResponseDto> toDto(PageResult<AcademicProgram> academicProgram);

    default AcademicProgramResponseDto.FacultyResponseDto toFacultyDto(Faculty faculty) {
        if (faculty == null) {
            return null;
        }
        return new AcademicProgramResponseDto.FacultyResponseDto(faculty.getId(), faculty.getName());
    }
}
