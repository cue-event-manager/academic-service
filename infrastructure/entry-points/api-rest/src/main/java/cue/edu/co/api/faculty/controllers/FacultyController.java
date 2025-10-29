package cue.edu.co.api.faculty.controllers;

import cue.edu.co.api.common.dtos.PaginationRequestDto;
import cue.edu.co.api.common.dtos.PaginationResponseDto;
import cue.edu.co.api.faculty.constants.FacultyEndpoint;
import cue.edu.co.api.faculty.dtos.*;
import cue.edu.co.api.faculty.mappers.FacultyDtoMapper;
import cue.edu.co.model.common.results.PageResult;
import cue.edu.co.model.faculty.Faculty;
import cue.edu.co.model.faculty.GetFacultyQuery;
import cue.edu.co.model.faculty.commands.CreateFacultyCommand;
import cue.edu.co.model.faculty.commands.DeleteFacultyCommand;
import cue.edu.co.model.faculty.commands.UpdateFacultyCommand;
import cue.edu.co.model.faculty.queries.FacultyPaginationQuery;
import cue.edu.co.usecase.faculty.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FacultyController {
    private final CreateFacultyUseCase createFacultyUseCase;
    private final UpdateFacultyUseCase updateFacultyUseCase;
    private final DeleteFacultyUseCase deleteFacultyUseCase;
    private final GetFacultyUseCase getFacultyUseCase;
    private final GetAllFacultiesUseCase getAllFacultiesUseCase;
    private final ExistsFacultyUseCase existsFacultyUseCase;

    private final FacultyDtoMapper facultyDtoMapper;

    @PostMapping(FacultyEndpoint.CREATE)
    public ResponseEntity<FacultyResponseDto> create(@Valid @RequestBody CreateFacultyRequestDto request) {
        CreateFacultyCommand command = facultyDtoMapper.toCommand(request);
        Faculty faculty = createFacultyUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(facultyDtoMapper.toDto(faculty));
    }

    @GetMapping(FacultyEndpoint.BASE_URL)
    public ResponseEntity<PaginationResponseDto<FacultyResponseDto>> getAll(
            @Valid FacultyPaginationRequestDto requestDto,
            @Valid PaginationRequestDto paginationRequestDto
    ) {
        FacultyPaginationQuery query = facultyDtoMapper.toQuery(requestDto, paginationRequestDto);
        PageResult<Faculty> pageResult = getAllFacultiesUseCase.execute(query);
        PaginationResponseDto<FacultyResponseDto> response = facultyDtoMapper.toDto(pageResult);
        return ResponseEntity.ok(response);
    }

    @GetMapping(FacultyEndpoint.GET_ALL)
    public ResponseEntity<List<FacultyResponseDto>> getAllForSelect() {
        List<FacultyResponseDto> faculties = getAllFacultiesUseCase
                .execute()
                .stream()
                .map(facultyDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(faculties);
    }

    @GetMapping(FacultyEndpoint.EXISTS_BY_ID)
    public ResponseEntity<ExistsFacultyResponseDto> existsById(@PathVariable(name = "id") Long id){
        Boolean exists = existsFacultyUseCase.execute(id);
        return ResponseEntity.ok(new ExistsFacultyResponseDto(exists));
    }

    @GetMapping(FacultyEndpoint.GET_BY_ID)
    public ResponseEntity<FacultyResponseDto> getById(@PathVariable(name = "id") Long id) {
        GetFacultyQuery query = new GetFacultyQuery(id);
        Faculty faculty = getFacultyUseCase.execute(query);
        return ResponseEntity.ok(facultyDtoMapper.toDto(faculty));
    }

    @PutMapping(FacultyEndpoint.UPDATE)
    public ResponseEntity<FacultyResponseDto> update(
            @PathVariable(name = "id") Long id,
            @Valid @RequestBody UpdateFacultyRequestDto request) {
        UpdateFacultyCommand command = facultyDtoMapper.toCommand(id, request);
        Faculty faculty = updateFacultyUseCase.execute(command);
        return ResponseEntity.ok(facultyDtoMapper.toDto(faculty));
    }

    @DeleteMapping(FacultyEndpoint.DELETE)
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        DeleteFacultyCommand command = new DeleteFacultyCommand(id);
        deleteFacultyUseCase.execute(command);
        return ResponseEntity.noContent().build();
    }
}
