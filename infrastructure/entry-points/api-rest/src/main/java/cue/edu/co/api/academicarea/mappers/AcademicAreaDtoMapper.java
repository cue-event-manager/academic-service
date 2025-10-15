package cue.edu.co.api.academicarea.mappers;

import cue.edu.co.api.academicarea.dtos.AcademicAreaPaginationRequestDto;
import cue.edu.co.api.academicarea.dtos.AcademicAreaResponseDto;
import cue.edu.co.api.academicarea.dtos.CreateAcademicAreaRequestDto;
import cue.edu.co.api.academicarea.dtos.UpdateAcademicAreaRequestDto;
import cue.edu.co.api.common.dtos.PaginationRequestDto;
import cue.edu.co.api.common.dtos.PaginationResponseDto;
import cue.edu.co.api.common.mappers.OptionalMapper;
import cue.edu.co.api.common.mappers.PaginationDtoMapper;
import cue.edu.co.model.academicarea.AcademicArea;
import cue.edu.co.model.academicarea.commands.CreateAcademicAreaCommand;
import cue.edu.co.model.academicarea.commands.UpdateAcademicAreaCommand;
import cue.edu.co.model.academicarea.queries.AcademicAreaPaginationQuery;
import cue.edu.co.model.common.results.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { OptionalMapper.class, PaginationDtoMapper.class })
public interface AcademicAreaDtoMapper {

    CreateAcademicAreaCommand toCommand(CreateAcademicAreaRequestDto dto);

    @Mapping(target = "id", source = "id")
    UpdateAcademicAreaCommand toCommand(Long id, UpdateAcademicAreaRequestDto dto);

    AcademicAreaResponseDto toDto(AcademicArea academicArea);

    @Mapping(target = "pagination", source = "paginationRequestDto")
    AcademicAreaPaginationQuery toQuery(AcademicAreaPaginationRequestDto academicAreaPaginationRequestDto, PaginationRequestDto paginationRequestDto);

    PaginationResponseDto<AcademicAreaResponseDto> toDto(PageResult<AcademicArea> academicArea);
}
