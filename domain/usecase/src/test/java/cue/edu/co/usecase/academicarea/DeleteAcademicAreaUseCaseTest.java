package cue.edu.co.usecase.academicarea;

import cue.edu.co.model.academicarea.AcademicArea;
import cue.edu.co.model.academicarea.commands.DeleteAcademicAreaCommand;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteAcademicAreaUseCaseTest {

    @Mock
    private AcademicAreaRepository academicAreaRepository;

    @InjectMocks
    private DeleteAcademicAreaUseCase deleteAcademicAreaUseCase;

    private DeleteAcademicAreaCommand command;
    private AcademicArea existingAcademicArea;

    @BeforeEach
    void setUp() {
        command = new DeleteAcademicAreaCommand(1L);

        existingAcademicArea = AcademicArea.builder()
                .id(1L)
                .name("Ingeniería")
                .description("Área de ingeniería")
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Test
    void shouldDeleteAcademicAreaSuccessfully() {
        // Arrange
        when(academicAreaRepository.findById(anyLong())).thenReturn(Optional.of(existingAcademicArea));
        doNothing().when(academicAreaRepository).deleteById(anyLong());

        // Act
        deleteAcademicAreaUseCase.execute(command);

        // Assert
        verify(academicAreaRepository, times(1)).findById(1L);
        verify(academicAreaRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenAcademicAreaNotFound() {
        // Arrange
        when(academicAreaRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(AcademicAreaNotFoundException.class, () -> deleteAcademicAreaUseCase.execute(command));

        verify(academicAreaRepository, times(1)).findById(1L);
        verify(academicAreaRepository, never()).deleteById(anyLong());
    }
}
