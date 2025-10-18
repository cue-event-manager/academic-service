package cue.edu.co.usecase.faculty;

import cue.edu.co.model.faculty.Faculty;
import cue.edu.co.model.faculty.GetFacultyQuery;
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
class GetFacultyUseCaseTest {

    @Mock
    private FacultyRepository facultyRepository;

    @InjectMocks
    private GetFacultyUseCase getFacultyUseCase;

    private GetFacultyQuery query;
    private Faculty faculty;

    @BeforeEach
    void setUp() {
        query = new GetFacultyQuery(1L);
        faculty = Faculty.builder()
                .id(1L)
                .name("Engineering")
                .description("Faculty of Engineering")
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Test
    void shouldGetFacultySuccessfully() {
        // Arrange
        when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));

        // Act
        Faculty result = getFacultyUseCase.execute(query);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Engineering", result.getName());
        assertEquals("Faculty of Engineering", result.getDescription());

        verify(facultyRepository, times(1)).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenFacultyNotFound() {
        // Arrange
        when(facultyRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(FacultyNotFoundException.class, () -> getFacultyUseCase.execute(query));

        verify(facultyRepository, times(1)).findById(1L);
    }
}
