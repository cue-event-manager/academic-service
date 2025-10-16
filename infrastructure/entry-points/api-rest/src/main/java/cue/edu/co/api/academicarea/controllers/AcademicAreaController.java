package cue.edu.co.api.academicarea.controllers;

import cue.edu.co.api.academicarea.constants.AcademicAreaEndpoint;
import cue.edu.co.api.academicarea.dtos.AcademicAreaPaginationRequestDto;
import cue.edu.co.api.academicarea.dtos.AcademicAreaResponseDto;
import cue.edu.co.api.academicarea.dtos.CreateAcademicAreaRequestDto;
import cue.edu.co.api.academicarea.dtos.UpdateAcademicAreaRequestDto;
import cue.edu.co.api.academicarea.mappers.AcademicAreaDtoMapper;
import cue.edu.co.api.common.dtos.PaginationRequestDto;
import cue.edu.co.api.common.dtos.PaginationResponseDto;
import cue.edu.co.model.academicarea.AcademicArea;
import cue.edu.co.model.academicarea.GetAcademicAreaQuery;
import cue.edu.co.model.academicarea.commands.CreateAcademicAreaCommand;
import cue.edu.co.model.academicarea.commands.DeleteAcademicAreaCommand;
import cue.edu.co.model.academicarea.commands.UpdateAcademicAreaCommand;
import cue.edu.co.model.academicarea.queries.AcademicAreaPaginationQuery;
import cue.edu.co.model.common.results.PageResult;
import cue.edu.co.usecase.academicarea.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AcademicAreaController {
    private final CreateAcademicAreaUseCase createAcademicAreaUseCase;
    private final UpdateAcademicAreaUseCase updateAcademicAreaUseCase;
    private final DeleteAcademicAreaUseCase deleteAcademicAreaUseCase;
    private final GetAcademicAreaUseCase getAcademicAreaUseCase;
    private final GetAllAcademicAreasUseCase getAllAcademicAreasUseCase;
    private final AcademicAreaDtoMapper academicAreaDtoMapper;

    @PostMapping(AcademicAreaEndpoint.ACADEMIC_AREA_CREATE_ENDPOINT)
    public ResponseEntity<AcademicAreaResponseDto> create(@Valid @RequestBody CreateAcademicAreaRequestDto request) {
        CreateAcademicAreaCommand command = academicAreaDtoMapper.toCommand(request);
        AcademicArea academicArea = createAcademicAreaUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(academicAreaDtoMapper.toDto(academicArea));
    }

    @GetMapping(AcademicAreaEndpoint.ACADEMIC_AREA_BASE)
    public ResponseEntity<PaginationResponseDto<AcademicAreaResponseDto>> getAll(
            @Valid AcademicAreaPaginationRequestDto requestDto,
            @Valid PaginationRequestDto paginationRequestDto
    ) {
        AcademicAreaPaginationQuery query = academicAreaDtoMapper.toQuery(requestDto, paginationRequestDto);
        PageResult<AcademicArea> pageResult = getAllAcademicAreasUseCase.execute(query);
        PaginationResponseDto<AcademicAreaResponseDto> response = academicAreaDtoMapper.toDto(pageResult);
        return ResponseEntity.ok(response);
    }

    @GetMapping(AcademicAreaEndpoint.ACADEMIC_AREA_GET_ALL)
    public ResponseEntity<List<AcademicAreaResponseDto>> getAll(
    ) {
        List<AcademicAreaResponseDto> academicAreas = getAllAcademicAreasUseCase
                .execute()
                .stream()
                .map(academicAreaDtoMapper::toDto)
                .toList();

        return ResponseEntity.ok(academicAreas);
    }

    @GetMapping(AcademicAreaEndpoint.ACADEMIC_AREA_BY_ID)
    public ResponseEntity<AcademicAreaResponseDto> getById(@PathVariable Long id) {
        GetAcademicAreaQuery query = new GetAcademicAreaQuery(id);
        AcademicArea academicArea = getAcademicAreaUseCase.execute(query);
        return ResponseEntity.ok(academicAreaDtoMapper.toDto(academicArea));
    }

    @PutMapping(AcademicAreaEndpoint.ACADEMIC_AREA_UPDATE_ENDPOINT)
    public ResponseEntity<AcademicAreaResponseDto> update(
            @PathVariable(name = "id") Long id,
            @Valid @RequestBody UpdateAcademicAreaRequestDto request) {
        UpdateAcademicAreaCommand command = academicAreaDtoMapper.toCommand(id, request);
        AcademicArea academicArea = updateAcademicAreaUseCase.execute(command);
        return ResponseEntity.ok(academicAreaDtoMapper.toDto(academicArea));
    }

    @DeleteMapping(AcademicAreaEndpoint.ACADEMIC_AREA_DELETE_ENDPOINT)
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        DeleteAcademicAreaCommand command = new DeleteAcademicAreaCommand(id);
        deleteAcademicAreaUseCase.execute(command);
        return ResponseEntity.noContent().build();
    }
}
