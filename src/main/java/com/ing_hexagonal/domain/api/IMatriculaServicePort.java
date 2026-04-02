package com.ing_hexagonal.domain.api;

import com.ing_hexagonal.domain.model.MatriculaModel;

import java.util.List;

public interface IMatriculaServicePort {

    void saveMatricula(MatriculaModel matriculaModel);
    void updateMatricula(MatriculaModel matriculaModel, Long id);
    void deleteMatricula(Long id);

    MatriculaModel getMatriculaById(Long id);
    List<MatriculaModel> getAllMatriculas();
    List<MatriculaModel> getMatriculasByEstudianteId(Long estudianteId);
}

/*
 * IMatriculaServicePort

    Tipo: Puerto de entrada
    Explicación:
        Define las operaciones relacionadas con las matrículas.

    Responsabilidad:
        Registrar matrícula
        Consultar matrículas
        Relacionar estudiante con curso/asignatura

**Representa los casos de uso del negocio relacionados con matrícula.
*
*  */
