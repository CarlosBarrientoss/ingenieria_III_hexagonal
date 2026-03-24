package com.ing_hexagonal.domain.api;


import com.ing_hexagonal.domain.model.EstudianteModel;

import java.util.List;

public interface IEstudianteServicePort {

    void saveEstudiante(EstudianteModel estudianteModel);
    void updateEstudiante(EstudianteModel estudianteModel, Long id);
    void deleteEstudiante(Long id);

    EstudianteModel getEstudianteById(Long id);
    List<EstudianteModel> getAllEstudiantes();
}

/*

IEstudianteServicePort

Tipo: Puerto de entrada (API)
Explicación:
Define qué puede hacer el sistema con los estudiantes desde el punto de vista del negocio.

Responsabilidad:
    Exponer operaciones como:
    guardar estudiante
    actualizar
    eliminar
    consultar

Idea clave:
    Es un contrato, no una implementación.
*
* */
