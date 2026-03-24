package com.ing_hexagonal.domain.spi;

import com.ing_hexagonal.domain.model.MatriculaModel;

import java.util.List;
import java.util.Optional;

public interface IMatriculaPersistencePort {

    void saveMatricula(MatriculaModel matriculaModel);
    void updateMatricula(MatriculaModel matriculaModel, Long id);
    void deleteMatricula(Long id);

    Optional<MatriculaModel> getMatriculaById(Long id);
    List<MatriculaModel> getAllMatriculas();
    List<MatriculaModel> getMatriculasByEstudianteId(Long estudianteId);

    boolean existsByCodigo(String codigo);
    boolean existsById(Long id);
}
