package cue.edu.co.seeder.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FacultySeedConstant {

    MEDICAL_SCIENCES("Facultad de Ciencias Médicas", "Incluye programas como Medicina."),

    ADMINISTRATIVE_SCIENCES("Facultad de Ciencias Administrativas",
            "Incluye programas como Administración de Empresas Dual, Marketing Digital & Comunicación Estratégica, y Tecnología en Gestión del Turismo Cultural y de Naturaleza."),

    ENGINEERING_AND_BASIC_SCIENCES("Facultad de Ingenierías y Ciencias Básicas",
            "Incluye programas como Ingeniería Industrial Dual, Ingeniería de Software Dual e Ingeniería Civil."),

    HUMAN_AND_EDUCATION_SCIENCES("Facultad de Ciencias Humanas y de la Educación",
            "Incluye programas como Psicología."),

    SOCIAL_AND_LEGAL_SCIENCES("Facultad de Ciencias Sociales y Jurídicas",
            "Incluye programas como Derecho."),

    HEALTH_SCIENCES("Facultad de Ciencias de la Salud",
            "Incluye programas como Enfermería."),

    AGRICULTURAL_SCIENCES("Facultad de Ciencias Agropecuarias",
            "Incluye programas como Medicina Veterinaria y Zootecnia.");

    private final String name;
    private final String description;
}
