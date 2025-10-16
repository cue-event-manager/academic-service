package cue.edu.co.model.academicprogram.commands;

public record CreateAcademicProgramCommand(
        String name,
        String description,
        Long facultyId
) {
}
