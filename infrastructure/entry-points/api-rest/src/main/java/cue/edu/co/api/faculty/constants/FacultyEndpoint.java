package cue.edu.co.api.faculty.constants;

public class FacultyEndpoint {
    private FacultyEndpoint() {}

    public static final String BASE_URL = "/api/faculties";
    public static final String GET_ALL = BASE_URL + "/all";
    public static final String GET_BY_ID = BASE_URL + "/{id}";
    public static final String CREATE = BASE_URL + "/create";
    public static final String UPDATE = BASE_URL + "/{id}/update";
    public static final String DELETE = BASE_URL + "/{id}/delete";
    public static final String EXISTS_BY_ID = BASE_URL + "/{id}/exists";

}
