package cue.edu.co.usecase.academicprogram;

import cue.edu.co.model.academicprogram.AcademicProgram;
import cue.edu.co.model.academicprogram.queries.GetAcademicProgramQuery;
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
class GetAcademicProgramUseCaseTest {

    @Mock
    private AcademicProgramRepository academicProgramRepository;

    @InjectMocks
    private GetAcademicProgramUseCase getAcademicProgramUseCase;

    private GetAcademicProgramQuery query;
    private AcademicProgram program;

    @BeforeEach
    void setUp() {
        query = new GetAcademicProgramQuery(1L);

        Faculty faculty = Faculty.builder()
                .id(1L)
                .name("Engineering Faculty")
                .description("Faculty of Engineering")
                .createdAt(LocalDateTime.now())
                .build();

        program = AcademicProgram.builder()
                .id(1L)
                .name("Computer Science")
                .description("Bachelor of Science in Computer Science")
                .faculty(faculty)
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Test
    void shouldGetAcademicProgramSuccessfully() {
        // Arrange
        when(academicProgramRepository.findById(anyLong())).thenReturn(Optional.of(program));

        // Act
        AcademicProgram result = getAcademicProgramUseCase.execute(query);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Computer Science", result.getName());
        assertEquals("Bachelor of Science in Computer Science", result.getDescription());
        assertNotNull(result.getFaculty());
        assertEquals("Engineering Faculty", result.getFaculty().getName());

        verify(academicProgramRepository, times(1)).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenProgramNotFound() {
        // Arrange
        when(academicProgramRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        AcademicProgramNotFoundException exception = assertThrows(
                AcademicProgramNotFoundException.class,
                () -> getAcademicProgramUseCase.execute(query)
        );

        assertTrue(exception.getMessage().contains("1"));
        verify(academicProgramRepository, times(1)).findById(1L);
    }
}
