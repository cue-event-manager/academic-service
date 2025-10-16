package cue.edu.co.usecase.faculty;

import cue.edu.co.model.faculty.Faculty;
import cue.edu.co.model.faculty.commands.CreateFacultyCommand;
import cue.edu.co.model.faculty.exceptions.DuplicateFacultyNameException;
import cue.edu.co.model.faculty.gateways.FacultyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateFacultyUseCaseTest {

    @Mock
    private FacultyRepository facultyRepository;

    @InjectMocks
    private CreateFacultyUseCase createFacultyUseCase;

    private CreateFacultyCommand command;

    @BeforeEach
    void setUp() {
        command = new CreateFacultyCommand("Engineering", "Faculty of Engineering");
    }

    @Test
    void shouldCreateFacultySuccessfully() {
        // Arrange
        when(facultyRepository.existsByName(anyString())).thenReturn(false);
        when(facultyRepository.save(any(Faculty.class))).thenAnswer(invocation -> {
            Faculty faculty = invocation.getArgument(0);
            return Faculty.builder()
                    .id(1L)
                    .name(faculty.getName())
                    .description(faculty.getDescription())
                    .createdAt(faculty.getCreatedAt())
                    .build();
        });

        // Act
        Faculty result = createFacultyUseCase.execute(command);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Engineering", result.getName());
        assertEquals("Faculty of Engineering", result.getDescription());
        assertNotNull(result.getCreatedAt());

        verify(facultyRepository, times(1)).existsByName("Engineering");
        verify(facultyRepository, times(1)).save(any(Faculty.class));
    }

    @Test
    void shouldThrowExceptionWhenNameAlreadyExists() {
        // Arrange
        when(facultyRepository.existsByName(anyString())).thenReturn(true);

        // Act & Assert
        DuplicateFacultyNameException exception = assertThrows(
                DuplicateFacultyNameException.class,
                () -> createFacultyUseCase.execute(command)
        );

        assertTrue(exception.getMessage().contains("Engineering"));
        verify(facultyRepository, times(1)).existsByName("Engineering");
        verify(facultyRepository, never()).save(any(Faculty.class));
    }
}
