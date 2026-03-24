package com.ing_hexagonal.domain.spi;

import com.ing_hexagonal.domain.model.EstudianteModel;

import java.util.List;
import java.util.Optional;

public interface IEstudiantePersistencePort {

    void saveEstudiante(EstudianteModel estudianteModel);
    void updateEstudiante(EstudianteModel estudianteModel, Long id);
    void deleteEstudiante(Long id);

    Optional<EstudianteModel> getEstudianteById(Long id);
    List<EstudianteModel> getAllEstudiantes();

    boolean existsByCorreo(String correo);
    boolean existsByDocumento(String documento);
    boolean existsById(Long id);
}
