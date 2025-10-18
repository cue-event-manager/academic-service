package cue.edu.co.api.faculty.mappers;

import cue.edu.co.api.common.dtos.PaginationRequestDto;
import cue.edu.co.api.common.dtos.PaginationResponseDto;
import cue.edu.co.api.common.mappers.OptionalMapper;
import cue.edu.co.api.common.mappers.PaginationDtoMapper;
import cue.edu.co.api.faculty.dtos.CreateFacultyRequestDto;
import cue.edu.co.api.faculty.dtos.FacultyPaginationRequestDto;
import cue.edu.co.api.faculty.dtos.FacultyResponseDto;
import cue.edu.co.api.faculty.dtos.UpdateFacultyRequestDto;
import cue.edu.co.model.common.results.PageResult;
import cue.edu.co.model.faculty.Faculty;
import cue.edu.co.model.faculty.commands.CreateFacultyCommand;
import cue.edu.co.model.faculty.commands.UpdateFacultyCommand;
import cue.edu.co.model.faculty.queries.FacultyPaginationQuery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { OptionalMapper.class, PaginationDtoMapper.class })
public interface FacultyDtoMapper {

    CreateFacultyCommand toCommand(CreateFacultyRequestDto dto);

    @Mapping(target = "id", source = "id")
    UpdateFacultyCommand toCommand(Long id, UpdateFacultyRequestDto dto);

    FacultyResponseDto toDto(Faculty faculty);

    @Mapping(target = "pagination", source = "paginationRequestDto")
    FacultyPaginationQuery toQuery(FacultyPaginationRequestDto facultyPaginationRequestDto, PaginationRequestDto paginationRequestDto);

    PaginationResponseDto<FacultyResponseDto> toDto(PageResult<Faculty> faculty);
}
