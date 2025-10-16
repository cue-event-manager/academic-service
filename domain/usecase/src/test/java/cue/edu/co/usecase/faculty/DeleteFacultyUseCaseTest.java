package cue.edu.co.usecase.faculty;

import cue.edu.co.model.common.BusinessException;
import cue.edu.co.model.faculty.Faculty;
import cue.edu.co.model.faculty.commands.DeleteFacultyCommand;
import cue.edu.co.model.faculty.exceptions.FacultyNotFoundException;
import cue.edu.co.model.faculty.gateways.FacultyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteFacultyUseCaseTest {

    @Mock
    private FacultyRepository facultyRepository;

    @InjectMocks
    private DeleteFacultyUseCase deleteFacultyUseCase;

    private DeleteFacultyCommand command;
    private Faculty faculty;

    @BeforeEach
    void setUp() {
        command = new DeleteFacultyCommand(1L);
        faculty = Faculty.builder()
                .id(1L)
                .name("Engineering")
                .description("Faculty of Engineering")
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Test
    void shouldDeleteFacultySuccessfully() {
        // Arrange
        when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));
        when(facultyRepository.hasActiveAcademicPrograms(1L)).thenReturn(false);
        doNothing().when(facultyRepository).deleteById(1L);

        // Act
        deleteFacultyUseCase.execute(command);

        // Assert
        verify(facultyRepository, times(1)).findById(1L);
        verify(facultyRepository, times(1)).hasActiveAcademicPrograms(1L);
        verify(facultyRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenFacultyNotFound() {
        // Arrange
        when(facultyRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(FacultyNotFoundException.class, () -> deleteFacultyUseCase.execute(command));

        verify(facultyRepository, times(1)).findById(1L);
        verify(facultyRepository, never()).hasActiveAcademicPrograms(anyLong());
        verify(facultyRepository, never()).deleteById(anyLong());
    }

    @Test
    void shouldThrowExceptionWhenFacultyHasActiveAcademicPrograms() {
        // Arrange
        when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));
        when(facultyRepository.hasActiveAcademicPrograms(1L)).thenReturn(true);

        // Act & Assert
        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> deleteFacultyUseCase.execute(command)
        );

        assertTrue(exception.getMessage().contains("Cannot delete faculty with active academic programs"));
        assertEquals("FACULTY_HAS_ACTIVE_PROGRAMS", exception.getCode());

        verify(facultyRepository, times(1)).findById(1L);
        verify(facultyRepository, times(1)).hasActiveAcademicPrograms(1L);
        verify(facultyRepository, never()).deleteById(anyLong());
    }
}
