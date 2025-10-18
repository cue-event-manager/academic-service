package cue.edu.co.usecase.academicprogram;

import cue.edu.co.model.academicprogram.AcademicProgram;
import cue.edu.co.model.academicprogram.commands.CreateAcademicProgramCommand;
import cue.edu.co.model.academicprogram.exceptions.DuplicateAcademicProgramNameException;
import cue.edu.co.model.academicprogram.gateways.AcademicProgramRepository;
import cue.edu.co.model.faculty.Faculty;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateAcademicProgramUseCaseTest {

    @Mock
    private AcademicProgramRepository academicProgramRepository;

    @Mock
    private FacultyRepository facultyRepository;

    @InjectMocks
    private CreateAcademicProgramUseCase createAcademicProgramUseCase;

    private CreateAcademicProgramCommand command;
    private Faculty faculty;

    @BeforeEach
    void setUp() {
        command = new CreateAcademicProgramCommand(
                "Computer Science",
                "Bachelor of Science in Computer Science",
                1L
        );

        faculty = Faculty.builder()
                .id(1L)
                .name("Engineering Faculty")
                .description("Faculty of Engineering")
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Test
    void shouldCreateAcademicProgramSuccessfully() {
        // Arrange
        when(facultyRepository.findById(anyLong())).thenReturn(Optional.of(faculty));
        when(academicProgramRepository.existsByNameAndFacultyId(anyString(), anyLong())).thenReturn(false);
        when(academicProgramRepository.save(any(AcademicProgram.class))).thenAnswer(invocation -> {
            AcademicProgram program = invocation.getArgument(0);
            return AcademicProgram.builder()
                    .id(1L)
                    .name(program.getName())
                    .description(program.getDescription())
                    .faculty(program.getFaculty())
                    .createdAt(program.getCreatedAt())
                    .build();
        });

        // Act
        AcademicProgram result = createAcademicProgramUseCase.execute(command);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Computer Science", result.getName());
        assertEquals("Bachelor of Science in Computer Science", result.getDescription());
        assertNotNull(result.getFaculty());
        assertEquals(1L, result.getFaculty().getId());
        assertNotNull(result.getCreatedAt());

        verify(facultyRepository, times(1)).findById(1L);
        verify(academicProgramRepository, times(1)).existsByNameAndFacultyId("Computer Science", 1L);
        verify(academicProgramRepository, times(1)).save(any(AcademicProgram.class));
    }

    @Test
    void shouldThrowExceptionWhenFacultyNotFound() {
        // Arrange
        when(facultyRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        FacultyNotFoundException exception = assertThrows(
                FacultyNotFoundException.class,
                () -> createAcademicProgramUseCase.execute(command)
        );

        assertTrue(exception.getMessage().contains("1"));
        verify(facultyRepository, times(1)).findById(1L);
        verify(academicProgramRepository, never()).existsByNameAndFacultyId(anyString(), anyLong());
        verify(academicProgramRepository, never()).save(any(AcademicProgram.class));
    }

    @Test
    void shouldThrowExceptionWhenNameAlreadyExistsInFaculty() {
        // Arrange
        when(facultyRepository.findById(anyLong())).thenReturn(Optional.of(faculty));
        when(academicProgramRepository.existsByNameAndFacultyId(anyString(), anyLong())).thenReturn(true);

        // Act & Assert
        DuplicateAcademicProgramNameException exception = assertThrows(
                DuplicateAcademicProgramNameException.class,
                () -> createAcademicProgramUseCase.execute(command)
        );

        assertTrue(exception.getMessage().contains("Computer Science"));
        verify(facultyRepository, times(1)).findById(1L);
        verify(academicProgramRepository, times(1)).existsByNameAndFacultyId("Computer Science", 1L);
        verify(academicProgramRepository, never()).save(any(AcademicProgram.class));
    }
}
