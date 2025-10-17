package cue.edu.co.usecase.academicprogram;

import cue.edu.co.model.academicprogram.AcademicProgram;
import cue.edu.co.model.academicprogram.commands.UpdateAcademicProgramCommand;
import cue.edu.co.model.academicprogram.exceptions.AcademicProgramNotFoundException;
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
class UpdateAcademicProgramUseCaseTest {

    @Mock
    private AcademicProgramRepository academicProgramRepository;

    @Mock
    private FacultyRepository facultyRepository;

    @InjectMocks
    private UpdateAcademicProgramUseCase updateAcademicProgramUseCase;

    private UpdateAcademicProgramCommand command;
    private AcademicProgram existingProgram;
    private Faculty faculty;
    private LocalDateTime createdAt;

    @BeforeEach
    void setUp() {
        createdAt = LocalDateTime.now().minusDays(10);

        faculty = Faculty.builder()
                .id(1L)
                .name("Engineering Faculty")
                .description("Faculty of Engineering")
                .createdAt(LocalDateTime.now())
                .build();

        existingProgram = AcademicProgram.builder()
                .id(1L)
                .name("Computer Science")
                .description("Bachelor of Science in Computer Science")
                .faculty(faculty)
                .createdAt(createdAt)
                .build();

        command = new UpdateAcademicProgramCommand(
                1L,
                "Software Engineering",
                "Bachelor of Science in Software Engineering",
                1L
        );
    }

    @Test
    void shouldUpdateAcademicProgramSuccessfully() {
        // Arrange
        when(academicProgramRepository.findById(anyLong())).thenReturn(Optional.of(existingProgram));
        when(facultyRepository.findById(anyLong())).thenReturn(Optional.of(faculty));
        when(academicProgramRepository.existsByNameAndFacultyIdAndIdNot(anyString(), anyLong(), anyLong())).thenReturn(false);
        when(academicProgramRepository.save(any(AcademicProgram.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        AcademicProgram result = updateAcademicProgramUseCase.execute(command);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Software Engineering", result.getName());
        assertEquals("Bachelor of Science in Software Engineering", result.getDescription());
        assertEquals(createdAt, result.getCreatedAt());
        assertNotNull(result.getFaculty());

        verify(academicProgramRepository, times(1)).findById(1L);
        verify(facultyRepository, times(1)).findById(1L);
        verify(academicProgramRepository, times(1)).existsByNameAndFacultyIdAndIdNot("Software Engineering", 1L, 1L);
        verify(academicProgramRepository, times(1)).save(any(AcademicProgram.class));
    }

    @Test
    void shouldThrowExceptionWhenProgramNotFound() {
        // Arrange
        when(academicProgramRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        AcademicProgramNotFoundException exception = assertThrows(
                AcademicProgramNotFoundException.class,
                () -> updateAcademicProgramUseCase.execute(command)
        );

        assertTrue(exception.getMessage().contains("1"));
        verify(academicProgramRepository, times(1)).findById(1L);
        verify(facultyRepository, never()).findById(anyLong());
        verify(academicProgramRepository, never()).save(any(AcademicProgram.class));
    }

    @Test
    void shouldThrowExceptionWhenFacultyNotFound() {
        // Arrange
        when(academicProgramRepository.findById(anyLong())).thenReturn(Optional.of(existingProgram));
        when(facultyRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        FacultyNotFoundException exception = assertThrows(
                FacultyNotFoundException.class,
                () -> updateAcademicProgramUseCase.execute(command)
        );

        assertTrue(exception.getMessage().contains("1"));
        verify(academicProgramRepository, times(1)).findById(1L);
        verify(facultyRepository, times(1)).findById(1L);
        verify(academicProgramRepository, never()).existsByNameAndFacultyIdAndIdNot(anyString(), anyLong(), anyLong());
        verify(academicProgramRepository, never()).save(any(AcademicProgram.class));
    }

    @Test
    void shouldThrowExceptionWhenNameAlreadyExistsInFaculty() {
        // Arrange
        when(academicProgramRepository.findById(anyLong())).thenReturn(Optional.of(existingProgram));
        when(facultyRepository.findById(anyLong())).thenReturn(Optional.of(faculty));
        when(academicProgramRepository.existsByNameAndFacultyIdAndIdNot(anyString(), anyLong(), anyLong())).thenReturn(true);

        // Act & Assert
        DuplicateAcademicProgramNameException exception = assertThrows(
                DuplicateAcademicProgramNameException.class,
                () -> updateAcademicProgramUseCase.execute(command)
        );

        assertTrue(exception.getMessage().contains("Software Engineering"));
        verify(academicProgramRepository, times(1)).findById(1L);
        verify(facultyRepository, times(1)).findById(1L);
        verify(academicProgramRepository, times(1)).existsByNameAndFacultyIdAndIdNot("Software Engineering", 1L, 1L);
        verify(academicProgramRepository, never()).save(any(AcademicProgram.class));
    }
}
