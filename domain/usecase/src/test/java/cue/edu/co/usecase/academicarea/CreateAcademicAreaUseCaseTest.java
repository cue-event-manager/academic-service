package cue.edu.co.usecase.academicarea;

import cue.edu.co.model.academicarea.AcademicArea;
import cue.edu.co.model.academicarea.commands.CreateAcademicAreaCommand;
import cue.edu.co.model.academicarea.exceptions.DuplicateAcademicAreaNameException;
import cue.edu.co.model.academicarea.gateways.AcademicAreaRepository;
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
class CreateAcademicAreaUseCaseTest {

    @Mock
    private AcademicAreaRepository academicAreaRepository;

    @InjectMocks
    private CreateAcademicAreaUseCase createAcademicAreaUseCase;

    private CreateAcademicAreaCommand command;

    @BeforeEach
    void setUp() {
        command = new CreateAcademicAreaCommand("Ingeniería", "Área de ingeniería y tecnología");
    }

    @Test
    void shouldCreateAcademicAreaSuccessfully() {
        // Arrange
        when(academicAreaRepository.existsByName(anyString())).thenReturn(false);
        when(academicAreaRepository.save(any(AcademicArea.class))).thenAnswer(invocation -> {
            AcademicArea academicArea = invocation.getArgument(0);
            return AcademicArea.builder()
                    .id(1L)
                    .name(academicArea.getName())
                    .description(academicArea.getDescription())
                    .createdAt(academicArea.getCreatedAt())
                    .build();
        });

        // Act
        AcademicArea result = createAcademicAreaUseCase.execute(command);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Ingeniería", result.getName());
        assertEquals("Área de ingeniería y tecnología", result.getDescription());
        assertNotNull(result.getCreatedAt());

        verify(academicAreaRepository, times(1)).existsByName("Ingeniería");
        verify(academicAreaRepository, times(1)).save(any(AcademicArea.class));
    }

    @Test
    void shouldThrowExceptionWhenNameAlreadyExists() {
        // Arrange
        when(academicAreaRepository.existsByName(anyString())).thenReturn(true);

        // Act & Assert
        DuplicateAcademicAreaNameException exception = assertThrows(
                DuplicateAcademicAreaNameException.class,
                () -> createAcademicAreaUseCase.execute(command)
        );

        assertTrue(exception.getMessage().contains("Ingeniería"));
        verify(academicAreaRepository, times(1)).existsByName("Ingeniería");
        verify(academicAreaRepository, never()).save(any(AcademicArea.class));
    }
}
