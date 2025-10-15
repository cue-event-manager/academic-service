package cue.edu.co.usecase.academicarea;

import cue.edu.co.model.academicarea.AcademicArea;
import cue.edu.co.model.academicarea.GetAcademicAreaQuery;
import cue.edu.co.model.academicarea.exceptions.AcademicAreaNotFoundException;
import cue.edu.co.model.academicarea.gateways.AcademicAreaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAcademicAreaUseCaseTest {

    @Mock
    private AcademicAreaRepository academicAreaRepository;

    @InjectMocks
    private GetAcademicAreaUseCase getAcademicAreaUseCase;

    private GetAcademicAreaQuery query;
    private AcademicArea academicArea;

    @BeforeEach
    void setUp() {
        query = new GetAcademicAreaQuery(1L);

        academicArea = AcademicArea.builder()
                .id(1L)
                .name("Ingeniería")
                .description("Área de ingeniería y tecnología")
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Test
    void shouldGetAcademicAreaSuccessfully() {
        // Arrange
        when(academicAreaRepository.findById(anyLong())).thenReturn(Optional.of(academicArea));

        // Act
        AcademicArea result = getAcademicAreaUseCase.execute(query);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Ingeniería", result.getName());
        assertEquals("Área de ingeniería y tecnología", result.getDescription());

        verify(academicAreaRepository, times(1)).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenAcademicAreaNotFound() {
        // Arrange
        when(academicAreaRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(AcademicAreaNotFoundException.class, () -> getAcademicAreaUseCase.execute(query));

        verify(academicAreaRepository, times(1)).findById(1L);
    }
}
