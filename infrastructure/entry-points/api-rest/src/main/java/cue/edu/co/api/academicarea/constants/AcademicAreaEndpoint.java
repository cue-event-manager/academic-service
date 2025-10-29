package cue.edu.co.api.academicarea.constants;

public class AcademicAreaEndpoint {
    private AcademicAreaEndpoint() {}

    public static final String ACADEMIC_AREA_BASE = "/api/academic-areas";
    public static final String ACADEMIC_AREA_GET_ALL = ACADEMIC_AREA_BASE + "/all";
    public static final String ACADEMIC_AREA_CREATE_ENDPOINT = ACADEMIC_AREA_BASE + "/create";
    public static final String ACADEMIC_AREA_BY_ID = ACADEMIC_AREA_BASE + "/{id}";
    public static final String ACADEMIC_AREA_UPDATE_ENDPOINT = ACADEMIC_AREA_BASE + "/{id}/update";
    public static final String ACADEMIC_AREA_DELETE_ENDPOINT = ACADEMIC_AREA_BASE + "/{id}/delete";
    public static final String ACADEMIC_AREA_EXISTS_ENDPOINT = ACADEMIC_AREA_BASE + "/{id}/exists";

}
