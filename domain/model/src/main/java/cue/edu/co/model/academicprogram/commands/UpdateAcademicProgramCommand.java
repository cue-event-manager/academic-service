package cue.edu.co.model.academicprogram.commands;

public record UpdateAcademicProgramCommand(
        Long id,
        String name,
        String description,
        Long facultyId
) {
}
