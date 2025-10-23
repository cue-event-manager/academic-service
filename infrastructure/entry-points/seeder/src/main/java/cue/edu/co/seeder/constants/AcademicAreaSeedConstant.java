package cue.edu.co.seeder.constants;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AcademicAreaSeedConstant {

    SOCIAL_PROJECTION(
            "Proyección Social",
            "Encargada de la vinculación con la comunidad y la responsabilidad social universitaria."
    ),
    INSTITUTIONAL_WELLBEING(
            "Bienestar Institucional",
            "Promueve la salud, recreación, cultura y acompañamiento integral a los estudiantes y docentes."
    ),
    RESEARCH_AND_INNOVATION(
            "Investigación e Innovación",
            "Coordina los semilleros, proyectos de investigación y alianzas científicas."
    ),
    INTERNATIONALIZATION(
            "Internacionalización",
            "Gestiona convenios internacionales, movilidad académica y cooperación."
    ),
    ACADEMIC_PRACTICES(
            "Prácticas Académicas y Empresariales",
            "Supervisa las prácticas profesionales y vinculación con empresas."
    ),
    QUALITY_ASSURANCE(
            "Aseguramiento de la Calidad Académica",
            "Lidera los procesos de autoevaluación, acreditación y mejora continua."
    );

    private final String name;
    private final String description;
}