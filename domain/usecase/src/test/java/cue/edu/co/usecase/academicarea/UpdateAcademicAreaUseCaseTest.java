package cue.edu.co.usecase.academicarea;

import cue.edu.co.model.academicarea.AcademicArea;
import cue.edu.co.model.academicarea.commands.UpdateAcademicAreaCommand;
import cue.edu.co.model.academicarea.exceptions.AcademicAreaNotFoundException;
import cue.edu.co.model.academicarea.exceptions.DuplicateAcademicAreaNameException;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateAcademicAreaUseCaseTest {

    @Mock
    private AcademicAreaRepository academicAreaRepository;

    @InjectMocks
    private UpdateAcademicAreaUseCase updateAcademicAreaUseCase;

    private UpdateAcademicAreaCommand command;
    private AcademicArea existingAcademicArea;

    @BeforeEach
    void setUp() {
        command = new UpdateAcademicAreaCommand(1L, "Ingeniería Actualizada", "Descripción actualizada");

        existingAcademicArea = AcademicArea.builder()
                .id(1L)
                .name("Ingeniería")
                .description("Descripción original")
                .createdAt(LocalDateTime.now().minusDays(1))
                .build();
    }

    @Test
    void shouldUpdateAcademicAreaSuccessfully() {
        // Arrange
        when(academicAreaRepository.findById(anyLong())).thenReturn(Optional.of(existingAcademicArea));
        when(academicAreaRepository.existsByNameAndIdNot(anyString(), anyLong())).thenReturn(false);
        when(academicAreaRepository.save(any(AcademicArea.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        AcademicArea result = updateAcademicAreaUseCase.execute(command);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Ingeniería Actualizada", result.getName());
        assertEquals("Descripción actualizada", result.getDescription());
        assertEquals(existingAcademicArea.getCreatedAt(), result.getCreatedAt());

        verify(academicAreaRepository, times(1)).findById(1L);
        verify(academicAreaRepository, times(1)).existsByNameAndIdNot("Ingeniería Actualizada", 1L);
        verify(academicAreaRepository, times(1)).save(any(AcademicArea.class));
    }

    @Test
    void shouldThrowExceptionWhenAcademicAreaNotFound() {
        // Arrange
        when(academicAreaRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(AcademicAreaNotFoundException.class, () -> updateAcademicAreaUseCase.execute(command));

        verify(academicAreaRepository, times(1)).findById(1L);
        verify(academicAreaRepository, never()).existsByNameAndIdNot(anyString(), anyLong());
        verify(academicAreaRepository, never()).save(any(AcademicArea.class));
    }

    @Test
    void shouldThrowExceptionWhenNameAlreadyExistsForAnotherAcademicArea() {
        // Arrange
        when(academicAreaRepository.findById(anyLong())).thenReturn(Optional.of(existingAcademicArea));
        when(academicAreaRepository.existsByNameAndIdNot(anyString(), anyLong())).thenReturn(true);

        // Act & Assert
        assertThrows(DuplicateAcademicAreaNameException.class, () -> updateAcademicAreaUseCase.execute(command));

        verify(academicAreaRepository, times(1)).findById(1L);
        verify(academicAreaRepository, times(1)).existsByNameAndIdNot("Ingeniería Actualizada", 1L);
        verify(academicAreaRepository, never()).save(any(AcademicArea.class));
    }
}
