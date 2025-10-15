package cue.edu.co.usecase.academicarea;

import cue.edu.co.model.academicarea.AcademicArea;
import cue.edu.co.model.academicarea.gateways.AcademicAreaRepository;
import cue.edu.co.model.academicarea.queries.AcademicAreaPaginationQuery;
import cue.edu.co.model.common.enums.SortDirection;
import cue.edu.co.model.common.queries.PaginationQuery;
import cue.edu.co.model.common.results.PageResult;
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
class GetAllAcademicAreasUseCaseTest {

    @Mock
    private AcademicAreaRepository academicAreaRepository;

    @InjectMocks
    private GetAllAcademicAreasUseCase getAllAcademicAreasUseCase;

    private AcademicAreaPaginationQuery query;
    private PageResult<AcademicArea> pageResult;

    @BeforeEach
    void setUp() {
        PaginationQuery paginationQuery = new PaginationQuery(0, 10, "name", SortDirection.ASC);
        query = new AcademicAreaPaginationQuery(Optional.empty(), paginationQuery);

        AcademicArea area1 = AcademicArea.builder()
                .id(1L)
                .name("Ingeniería")
                .description("Área de ingeniería")
                .createdAt(LocalDateTime.now())
                .build();

        AcademicArea area2 = AcademicArea.builder()
                .id(2L)
                .name("Ciencias Sociales")
                .description("Área de ciencias sociales")
                .createdAt(LocalDateTime.now())
                .build();

        pageResult = new PageResult<>(
                List.of(area1, area2),
                0,
                10,
                2L,
                1,
                false,
                false
        );
    }

    @Test
    void shouldGetAllAcademicAreasSuccessfully() {
        // Arrange
        when(academicAreaRepository.findAllByFilters(any(AcademicAreaPaginationQuery.class))).thenReturn(pageResult);

        // Act
        PageResult<AcademicArea> result = getAllAcademicAreasUseCase.execute(query);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.items().size());
        assertEquals(0, result.page());
        assertEquals(10, result.size());
        assertEquals(2L, result.totalElements());
        assertEquals(1, result.totalPages());
        assertFalse(result.hasNext());
        assertFalse(result.hasPrevious());

        verify(academicAreaRepository, times(1)).findAllByFilters(query);
    }

    @Test
    void shouldGetAcademicAreasWithFilters() {
        // Arrange
        PaginationQuery paginationQuery = new PaginationQuery(0, 10, "name", SortDirection.ASC);
        AcademicAreaPaginationQuery filteredQuery = new AcademicAreaPaginationQuery(
                Optional.of("Ingeniería"),
                paginationQuery
        );

        AcademicArea area1 = AcademicArea.builder()
                .id(1L)
                .name("Ingeniería")
                .description("Área de ingeniería")
                .createdAt(LocalDateTime.now())
                .build();

        PageResult<AcademicArea> filteredResult = new PageResult<>(
                List.of(area1),
                0,
                10,
                1L,
                1,
                false,
                false
        );

        when(academicAreaRepository.findAllByFilters(any(AcademicAreaPaginationQuery.class))).thenReturn(filteredResult);

        // Act
        PageResult<AcademicArea> result = getAllAcademicAreasUseCase.execute(filteredQuery);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.items().size());
        assertEquals("Ingeniería", result.items().get(0).getName());

        verify(academicAreaRepository, times(1)).findAllByFilters(filteredQuery);
    }
}
