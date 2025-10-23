package cue.edu.co.seeder.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AcademicProgramSeedConstant {

    MEDICINE(
            "Medicina",
            "Programa de formación en Ciencias Médicas.",
            FacultySeedConstant.MEDICAL_SCIENCES.getName()
    ),

    BUSINESS_ADMINISTRATION_DUAL(
            "Administración de Empresas Dual",
            "Programa de formación en gestión empresarial con modalidad dual.",
            FacultySeedConstant.ADMINISTRATIVE_SCIENCES.getName()
    ),
    DIGITAL_MARKETING(
            "Marketing Digital & Comunicación Estratégica",
            "Programa orientado a la comunicación y el marketing en entornos digitales.",
            FacultySeedConstant.ADMINISTRATIVE_SCIENCES.getName()
    ),
    TOURISM_MANAGEMENT(
            "Tecnología en Gestión del Turismo Cultural y de Naturaleza",
            "Programa tecnológico en turismo cultural y natural.",
            FacultySeedConstant.ADMINISTRATIVE_SCIENCES.getName()
    ),

    INDUSTRIAL_ENGINEERING_DUAL(
            "Ingeniería Industrial Dual",
            "Programa enfocado en la optimización de procesos industriales con modalidad dual.",
            FacultySeedConstant.ENGINEERING_AND_BASIC_SCIENCES.getName()
    ),
    SOFTWARE_ENGINEERING_DUAL(
            "Ingeniería de Software Dual",
            "Programa especializado en desarrollo de software bajo modalidad dual.",
            FacultySeedConstant.ENGINEERING_AND_BASIC_SCIENCES.getName()
    ),
    CIVIL_ENGINEERING(
            "Ingeniería Civil",
            "Programa de formación en diseño y construcción de infraestructura civil.",
            FacultySeedConstant.ENGINEERING_AND_BASIC_SCIENCES.getName()
    ),

    PSYCHOLOGY(
            "Psicología",
            "Programa enfocado en la comprensión del comportamiento humano.",
            FacultySeedConstant.HUMAN_AND_EDUCATION_SCIENCES.getName()
    ),

    LAW(
            "Derecho",
            "Programa de formación jurídica y práctica legal.",
            FacultySeedConstant.SOCIAL_AND_LEGAL_SCIENCES.getName()
    ),

    NURSING(
            "Enfermería",
            "Programa de formación integral en el cuidado de la salud.",
            FacultySeedConstant.HEALTH_SCIENCES.getName()
    ),

    VETERINARY(
            "Medicina Veterinaria y Zootecnia",
            "Programa orientado al bienestar y producción animal.",
            FacultySeedConstant.AGRICULTURAL_SCIENCES.getName()
    );

    private final String name;
    private final String description;
    private final String facultyName;
}