package cue.edu.co.usecase.academicprogram;

import cue.edu.co.model.academicprogram.AcademicProgram;
import cue.edu.co.model.academicprogram.commands.DeleteAcademicProgramCommand;
import cue.edu.co.model.academicprogram.exceptions.AcademicProgramNotFoundException;
import cue.edu.co.model.academicprogram.gateways.AcademicProgramRepository;
import cue.edu.co.model.faculty.Faculty;
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
class DeleteAcademicProgramUseCaseTest {

    @Mock
    private AcademicProgramRepository academicProgramRepository;

    @InjectMocks
    private DeleteAcademicProgramUseCase deleteAcademicProgramUseCase;

    private DeleteAcademicProgramCommand command;
    private AcademicProgram existingProgram;

    @BeforeEach
    void setUp() {
        command = new DeleteAcademicProgramCommand(1L);

        Faculty faculty = Faculty.builder()
                .id(1L)
                .name("Engineering Faculty")
                .createdAt(LocalDateTime.now())
                .build();

        existingProgram = AcademicProgram.builder()
                .id(1L)
                .name("Computer Science")
                .description("Bachelor of Science in Computer Science")
                .faculty(faculty)
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Test
    void shouldDeleteAcademicProgramSuccessfully() {
        // Arrange
        when(academicProgramRepository.findById(anyLong())).thenReturn(Optional.of(existingProgram));
        doNothing().when(academicProgramRepository).deleteById(anyLong());

        // Act
        assertDoesNotThrow(() -> deleteAcademicProgramUseCase.execute(command));

        // Assert
        verify(academicProgramRepository, times(1)).findById(1L);
        verify(academicProgramRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenProgramNotFound() {
        // Arrange
        when(academicProgramRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        AcademicProgramNotFoundException exception = assertThrows(
                AcademicProgramNotFoundException.class,
                () -> deleteAcademicProgramUseCase.execute(command)
        );

        assertTrue(exception.getMessage().contains("1"));
        verify(academicProgramRepository, times(1)).findById(1L);
        verify(academicProgramRepository, never()).deleteById(anyLong());
    }
}
