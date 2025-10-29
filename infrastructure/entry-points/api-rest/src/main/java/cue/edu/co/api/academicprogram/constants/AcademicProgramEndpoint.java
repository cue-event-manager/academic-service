package cue.edu.co.api.academicprogram.constants;

public class AcademicProgramEndpoint {
    private AcademicProgramEndpoint() {}

    public static final String ACADEMIC_PROGRAM_BASE = "/api/academic-programs";
    public static final String ACADEMIC_PROGRAM_GET_ALL = ACADEMIC_PROGRAM_BASE + "/all";
    public static final String ACADEMIC_PROGRAM_CREATE_ENDPOINT = ACADEMIC_PROGRAM_BASE + "/create";
    public static final String ACADEMIC_PROGRAM_BY_ID = ACADEMIC_PROGRAM_BASE + "/{id}";
    public static final String ACADEMIC_PROGRAM_UPDATE_ENDPOINT = ACADEMIC_PROGRAM_BASE + "/{id}/update";
    public static final String ACADEMIC_PROGRAM_DELETE_ENDPOINT = ACADEMIC_PROGRAM_BASE + "/{id}/delete";
    public static final String ACADEMIC_PROGRAM_EXISTS_ENDPOINT = ACADEMIC_PROGRAM_BASE + "/{id}/exists";
}
