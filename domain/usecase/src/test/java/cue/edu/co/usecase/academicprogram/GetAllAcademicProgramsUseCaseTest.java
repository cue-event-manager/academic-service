package cue.edu.co.usecase.academicprogram;

import cue.edu.co.model.academicprogram.AcademicProgram;
import cue.edu.co.model.academicprogram.gateways.AcademicProgramRepository;
import cue.edu.co.model.academicprogram.queries.AcademicProgramPaginationQuery;
import cue.edu.co.model.common.enums.SortDirection;
import cue.edu.co.model.common.queries.PaginationQuery;
import cue.edu.co.model.common.results.PageResult;
import cue.edu.co.model.faculty.Faculty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllAcademicProgramsUseCaseTest {

    @Mock
    private AcademicProgramRepository academicProgramRepository;

    @InjectMocks
    private GetAllAcademicProgramsUseCase getAllAcademicProgramsUseCase;

    private AcademicProgramPaginationQuery query;
    private PageResult<AcademicProgram> pageResult;
    private Faculty faculty;

    @BeforeEach
    void setUp() {
        PaginationQuery paginationQuery = new PaginationQuery(0, 10, "name", SortDirection.ASC);
        query = new AcademicProgramPaginationQuery(Optional.empty(), Optional.empty(), paginationQuery);

        faculty = Faculty.builder()
                .id(1L)
                .name("Engineering Faculty")
                .description("Faculty of Engineering")
                .createdAt(LocalDateTime.now())
                .build();

        AcademicProgram program1 = AcademicProgram.builder()
                .id(1L)
                .name("Computer Science")
                .description("Bachelor of Science in Computer Science")
                .faculty(faculty)
                .createdAt(LocalDateTime.now())
                .build();

        AcademicProgram program2 = AcademicProgram.builder()
                .id(2L)
                .name("Software Engineering")
                .description("Bachelor of Science in Software Engineering")
                .faculty(faculty)
                .createdAt(LocalDateTime.now())
                .build();

        pageResult = new PageResult<>(
                List.of(program1, program2),
                0,
                10,
                2L,
                1,
                false,
                false
        );
    }

    @Test
    void shouldGetAllAcademicProgramsSuccessfully() {
        // Arrange
        when(academicProgramRepository.findAllByFilters(any(AcademicProgramPaginationQuery.class))).thenReturn(pageResult);

        // Act
        PageResult<AcademicProgram> result = getAllAcademicProgramsUseCase.execute(query);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.items().size());
        assertEquals(0, result.page());
        assertEquals(10, result.size());
        assertEquals(2L, result.totalElements());
        assertEquals(1, result.totalPages());
        assertFalse(result.hasNext());
        assertFalse(result.hasPrevious());

        verify(academicProgramRepository, times(1)).findAllByFilters(query);
    }

    @Test
    void shouldGetAcademicProgramsWithNameFilter() {
        // Arrange
        PaginationQuery paginationQuery = new PaginationQuery(0, 10, "name", SortDirection.ASC);
        AcademicProgramPaginationQuery filteredQuery = new AcademicProgramPaginationQuery(
                Optional.of("Computer Science"),
                Optional.empty(),
                paginationQuery
        );

        AcademicProgram program1 = AcademicProgram.builder()
                .id(1L)
                .name("Computer Science")
                .description("Bachelor of Science in Computer Science")
                .faculty(faculty)
                .createdAt(LocalDateTime.now())
                .build();

        PageResult<AcademicProgram> filteredResult = new PageResult<>(
                List.of(program1),
                0,
                10,
                1L,
                1,
                false,
                false
        );

        when(academicProgramRepository.findAllByFilters(any(AcademicProgramPaginationQuery.class))).thenReturn(filteredResult);

        // Act
        PageResult<AcademicProgram> result = getAllAcademicProgramsUseCase.execute(filteredQuery);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.items().size());
        assertEquals("Computer Science", result.items().get(0).getName());

        verify(academicProgramRepository, times(1)).findAllByFilters(filteredQuery);
    }

    @Test
    void shouldGetAcademicProgramsWithFacultyIdFilter() {
        // Arrange
        PaginationQuery paginationQuery = new PaginationQuery(0, 10, "name", SortDirection.ASC);
        AcademicProgramPaginationQuery filteredQuery = new AcademicProgramPaginationQuery(
                Optional.empty(),
                Optional.of(1L),
                paginationQuery
        );

        when(academicProgramRepository.findAllByFilters(any(AcademicProgramPaginationQuery.class))).thenReturn(pageResult);

        // Act
        PageResult<AcademicProgram> result = getAllAcademicProgramsUseCase.execute(filteredQuery);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.items().size());
        assertEquals(1L, result.items().get(0).getFaculty().getId());
        assertEquals(1L, result.items().get(1).getFaculty().getId());

        verify(academicProgramRepository, times(1)).findAllByFilters(filteredQuery);
    }

    @Test
    void shouldReturnEmptyPageWhenNoResults() {
        // Arrange
        PageResult<AcademicProgram> emptyResult = new PageResult<>(
                List.of(),
                0,
                10,
                0L,
                0,
                false,
                false
        );

        when(academicProgramRepository.findAllByFilters(any(AcademicProgramPaginationQuery.class))).thenReturn(emptyResult);

        // Act
        PageResult<AcademicProgram> result = getAllAcademicProgramsUseCase.execute(query);

        // Assert
        assertNotNull(result);
        assertTrue(result.items().isEmpty());
        assertEquals(0L, result.totalElements());
        assertEquals(0, result.totalPages());

        verify(academicProgramRepository, times(1)).findAllByFilters(query);
    }
}
