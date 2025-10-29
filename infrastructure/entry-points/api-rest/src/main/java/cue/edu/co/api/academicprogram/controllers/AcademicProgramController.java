package cue.edu.co.api.academicprogram.controllers;

import cue.edu.co.api.academicprogram.constants.AcademicProgramEndpoint;
import cue.edu.co.api.academicprogram.dtos.AcademicProgramPaginationRequestDto;
import cue.edu.co.api.academicprogram.dtos.AcademicProgramResponseDto;
import cue.edu.co.api.academicprogram.dtos.CreateAcademicProgramRequestDto;
import cue.edu.co.api.academicprogram.dtos.UpdateAcademicProgramRequestDto;
import cue.edu.co.api.academicprogram.mappers.AcademicProgramDtoMapper;
import cue.edu.co.api.common.dtos.PaginationRequestDto;
import cue.edu.co.api.common.dtos.PaginationResponseDto;
import cue.edu.co.model.academicprogram.AcademicProgram;
import cue.edu.co.model.academicprogram.GetAcademicProgramQuery;
import cue.edu.co.model.academicprogram.commands.CreateAcademicProgramCommand;
import cue.edu.co.model.academicprogram.commands.DeleteAcademicProgramCommand;
import cue.edu.co.model.academicprogram.commands.UpdateAcademicProgramCommand;
import cue.edu.co.model.academicprogram.queries.AcademicProgramPaginationQuery;
import cue.edu.co.model.common.results.PageResult;
import cue.edu.co.usecase.academicprogram.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AcademicProgramController {
    private final CreateAcademicProgramUseCase createAcademicProgramUseCase;
    private final UpdateAcademicProgramUseCase updateAcademicProgramUseCase;
    private final DeleteAcademicProgramUseCase deleteAcademicProgramUseCase;
    private final GetAcademicProgramUseCase getAcademicProgramUseCase;
    private final GetAllAcademicProgramsUseCase getAllAcademicProgramsUseCase;
    private final AcademicProgramDtoMapper academicProgramDtoMapper;

    @PostMapping(AcademicProgramEndpoint.ACADEMIC_PROGRAM_CREATE_ENDPOINT)
    public ResponseEntity<AcademicProgramResponseDto> create(@Valid @RequestBody CreateAcademicProgramRequestDto request) {
        CreateAcademicProgramCommand command = academicProgramDtoMapper.toCommand(request);
        AcademicProgram academicProgram = createAcademicProgramUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(academicProgramDtoMapper.toDto(academicProgram));
    }

    @GetMapping(AcademicProgramEndpoint.ACADEMIC_PROGRAM_BASE)
    public ResponseEntity<PaginationResponseDto<AcademicProgramResponseDto>> getAll(
            @Valid AcademicProgramPaginationRequestDto requestDto,
            @Valid PaginationRequestDto paginationRequestDto
    ) {
        AcademicProgramPaginationQuery query = academicProgramDtoMapper.toQuery(requestDto, paginationRequestDto);
        PageResult<AcademicProgram> pageResult = getAllAcademicProgramsUseCase.execute(query);
        PaginationResponseDto<AcademicProgramResponseDto> response = academicProgramDtoMapper.toDto(pageResult);
        return ResponseEntity.ok(response);
    }

    @GetMapping(AcademicProgramEndpoint.ACADEMIC_PROGRAM_GET_ALL)
    public ResponseEntity<List<AcademicProgramResponseDto>> getAll() {
        List<AcademicProgramResponseDto> academicPrograms = getAllAcademicProgramsUseCase
                .execute()
                .stream()
                .map(academicProgramDtoMapper::toDto)
                .toList();

        return ResponseEntity.ok(academicPrograms);
    }

    @GetMapping(AcademicProgramEndpoint.ACADEMIC_PROGRAM_BY_ID)
    public ResponseEntity<AcademicProgramResponseDto> getById(@PathVariable Long id) {
        GetAcademicProgramQuery query = new GetAcademicProgramQuery(id);
        AcademicProgram academicProgram = getAcademicProgramUseCase.execute(query);
        return ResponseEntity.ok(academicProgramDtoMapper.toDto(academicProgram));
    }

    @PutMapping(AcademicProgramEndpoint.ACADEMIC_PROGRAM_UPDATE_ENDPOINT)
    public ResponseEntity<AcademicProgramResponseDto> update(
            @PathVariable(name = "id") Long id,
            @Valid @RequestBody UpdateAcademicProgramRequestDto request) {
        UpdateAcademicProgramCommand command = academicProgramDtoMapper.toCommand(id, request);
        AcademicProgram academicProgram = updateAcademicProgramUseCase.execute(command);
        return ResponseEntity.ok(academicProgramDtoMapper.toDto(academicProgram));
    }

    @DeleteMapping(AcademicProgramEndpoint.ACADEMIC_PROGRAM_DELETE_ENDPOINT)
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        DeleteAcademicProgramCommand command = new DeleteAcademicProgramCommand(id);
        deleteAcademicProgramUseCase.execute(command);
        return ResponseEntity.noContent().build();
    }
}
