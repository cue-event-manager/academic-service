package cue.edu.co.usecase.faculty;

import cue.edu.co.model.faculty.Faculty;
import cue.edu.co.model.faculty.commands.UpdateFacultyCommand;
import cue.edu.co.model.faculty.exceptions.DuplicateFacultyNameException;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateFacultyUseCaseTest {

    @Mock
    private FacultyRepository facultyRepository;

    @InjectMocks
    private UpdateFacultyUseCase updateFacultyUseCase;

    private UpdateFacultyCommand command;
    private Faculty existingFaculty;

    @BeforeEach
    void setUp() {
        command = new UpdateFacultyCommand(1L, "Engineering Updated", "Updated description");
        existingFaculty = Faculty.builder()
                .id(1L)
                .name("Engineering")
                .description("Faculty of Engineering")
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Test
    void shouldUpdateFacultySuccessfully() {
        // Arrange
        when(facultyRepository.findById(1L)).thenReturn(Optional.of(existingFaculty));
        when(facultyRepository.existsByNameAndIdNot(anyString(), anyLong())).thenReturn(false);
        when(facultyRepository.save(any(Faculty.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Faculty result = updateFacultyUseCase.execute(command);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Engineering Updated", result.getName());
        assertEquals("Updated description", result.getDescription());
        assertEquals(existingFaculty.getCreatedAt(), result.getCreatedAt());

        verify(facultyRepository, times(1)).findById(1L);
        verify(facultyRepository, times(1)).existsByNameAndIdNot("Engineering Updated", 1L);
        verify(facultyRepository, times(1)).save(any(Faculty.class));
    }

    @Test
    void shouldThrowExceptionWhenFacultyNotFound() {
        // Arrange
        when(facultyRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(FacultyNotFoundException.class, () -> updateFacultyUseCase.execute(command));

        verify(facultyRepository, times(1)).findById(1L);
        verify(facultyRepository, never()).existsByNameAndIdNot(anyString(), anyLong());
        verify(facultyRepository, never()).save(any(Faculty.class));
    }

    @Test
    void shouldThrowExceptionWhenNameAlreadyExists() {
        // Arrange
        when(facultyRepository.findById(1L)).thenReturn(Optional.of(existingFaculty));
        when(facultyRepository.existsByNameAndIdNot(anyString(), anyLong())).thenReturn(true);

        // Act & Assert
        assertThrows(DuplicateFacultyNameException.class, () -> updateFacultyUseCase.execute(command));

        verify(facultyRepository, times(1)).findById(1L);
        verify(facultyRepository, times(1)).existsByNameAndIdNot("Engineering Updated", 1L);
        verify(facultyRepository, never()).save(any(Faculty.class));
    }
}
