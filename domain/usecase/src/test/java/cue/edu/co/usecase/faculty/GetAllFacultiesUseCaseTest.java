package cue.edu.co.usecase.faculty;

import cue.edu.co.model.common.enums.SortDirection;
import cue.edu.co.model.common.queries.PaginationQuery;
import cue.edu.co.model.common.results.PageResult;
import cue.edu.co.model.faculty.Faculty;
import cue.edu.co.model.faculty.gateways.FacultyRepository;
import cue.edu.co.model.faculty.queries.FacultyPaginationQuery;
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
class GetAllFacultiesUseCaseTest {

    @Mock
    private FacultyRepository facultyRepository;

    @InjectMocks
    private GetAllFacultiesUseCase getAllFacultiesUseCase;

    private FacultyPaginationQuery query;
    private PageResult<Faculty> pageResult;

    @BeforeEach
    void setUp() {
        PaginationQuery paginationQuery = new PaginationQuery(0, 10, "name", SortDirection.ASC);
        query = new FacultyPaginationQuery(Optional.empty(), paginationQuery);

        Faculty faculty1 = Faculty.builder()
                .id(1L)
                .name("Engineering")
                .description("Faculty of Engineering")
                .createdAt(LocalDateTime.now())
                .build();

        Faculty faculty2 = Faculty.builder()
                .id(2L)
                .name("Sciences")
                .description("Faculty of Sciences")
                .createdAt(LocalDateTime.now())
                .build();

        pageResult = new PageResult<>(List.of(faculty1, faculty2), 0, 10, 2, 1, false, false);
    }

    @Test
    void shouldGetAllFacultiesSuccessfully() {
        // Arrange
        when(facultyRepository.findAllByFilters(any(FacultyPaginationQuery.class))).thenReturn(pageResult);

        // Act
        PageResult<Faculty> result = getAllFacultiesUseCase.execute(query);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.items().size());
        assertEquals(0, result.page());
        assertEquals(10, result.size());
        assertEquals(2, result.totalElements());
        assertEquals(1, result.totalPages());

        verify(facultyRepository, times(1)).findAllByFilters(query);
    }

    @Test
    void shouldGetAllFacultiesWithNameFilter() {
        // Arrange
        PaginationQuery paginationQuery = new PaginationQuery(0, 10, "name", SortDirection.ASC);
        FacultyPaginationQuery queryWithFilter = new FacultyPaginationQuery(
                Optional.of("Engineering"),
                paginationQuery
        );

        Faculty faculty = Faculty.builder()
                .id(1L)
                .name("Engineering")
                .description("Faculty of Engineering")
                .createdAt(LocalDateTime.now())
                .build();

        PageResult<Faculty> filteredResult = new PageResult<>(List.of(faculty), 0, 10, 1, 1, false, false);
        when(facultyRepository.findAllByFilters(any(FacultyPaginationQuery.class))).thenReturn(filteredResult);

        // Act
        PageResult<Faculty> result = getAllFacultiesUseCase.execute(queryWithFilter);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.items().size());
        assertEquals("Engineering", result.items().get(0).getName());

        verify(facultyRepository, times(1)).findAllByFilters(queryWithFilter);
    }
}
