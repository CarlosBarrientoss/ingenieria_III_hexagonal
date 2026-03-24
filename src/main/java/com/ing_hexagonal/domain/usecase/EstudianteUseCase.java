package com.ing_hexagonal.domain.usecase;

import com.ing_hexagonal.domain.api.IEstudianteServicePort;
import com.ing_hexagonal.domain.exception.DomainException;
import com.ing_hexagonal.domain.model.EstudianteModel;
import com.ing_hexagonal.domain.spi.IEstudiantePersistencePort;
import com.ing_hexagonal.domain.spi.IMatriculaPersistencePort;

import java.util.List;

public class EstudianteUseCase implements IEstudianteServicePort {

    private final IEstudiantePersistencePort estudiantePersistencePort;
    private final IMatriculaPersistencePort matriculaPersistencePort;

    public EstudianteUseCase(IEstudiantePersistencePort estudiantePersistencePort,
                             IMatriculaPersistencePort matriculaPersistencePort) {
        this.estudiantePersistencePort = estudiantePersistencePort;
        this.matriculaPersistencePort = matriculaPersistencePort;
    }

    @Override
    public void saveEstudiante(EstudianteModel estudianteModel) {
        validateEstudiante(estudianteModel);

        if (estudiantePersistencePort.existsByCorreo(estudianteModel.getCorreo())) {
            throw new DomainException("Ya existe un estudiante con ese correo");
        }

        if (estudiantePersistencePort.existsByDocumento(estudianteModel.getDocumento())) {
            throw new DomainException("Ya existe un estudiante con ese documento");
        }

        estudiantePersistencePort.saveEstudiante(estudianteModel);
    }

    @Override
    public void updateEstudiante(EstudianteModel estudianteModel, Long id) {
        if (id == null) {
            throw new DomainException("El id del estudiante es obligatorio");
        }

        if (!estudiantePersistencePort.existsById(id)) {
            throw new DomainException("El estudiante no existe");
        }

        validateEstudiante(estudianteModel);
        estudiantePersistencePort.updateEstudiante(estudianteModel, id);
    }

    @Override
    public void deleteEstudiante(Long id) {
        if (id == null) {
            throw new DomainException("El id del estudiante es obligatorio");
        }

        if (!estudiantePersistencePort.existsById(id)) {
            throw new DomainException("El estudiante no existe");
        }

        List<?> matriculas = matriculaPersistencePort.getMatriculasByEstudianteId(id);
        if (!matriculas.isEmpty()) {
            throw new DomainException("No se puede eliminar el estudiante porque tiene matrículas asociadas");
        }

        estudiantePersistencePort.deleteEstudiante(id);
    }

    @Override
    public EstudianteModel getEstudianteById(Long id) {
        if (id == null) {
            throw new DomainException("El id del estudiante es obligatorio");
        }

        return estudiantePersistencePort.getEstudianteById(id)
                .orElseThrow(() -> new DomainException("Estudiante no encontrado"));
    }

    @Override
    public List<EstudianteModel> getAllEstudiantes() {
        return estudiantePersistencePort.getAllEstudiantes();
    }

    private void validateEstudiante(EstudianteModel estudianteModel) {
        if (estudianteModel == null) {
            throw new DomainException("El estudiante no puede ser nulo");
        }

        if (estudianteModel.getNombres() == null || estudianteModel.getNombres().trim().isEmpty()) {
            throw new DomainException("Los nombres son obligatorios");
        }

        if (estudianteModel.getApellidos() == null || estudianteModel.getApellidos().trim().isEmpty()) {
            throw new DomainException("Los apellidos son obligatorios");
        }

        if (estudianteModel.getCorreo() == null || estudianteModel.getCorreo().trim().isEmpty()) {
            throw new DomainException("El correo es obligatorio");
        }

        if (!estudianteModel.getCorreo().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new DomainException("El correo no tiene un formato válido");
        }

        if (estudianteModel.getDocumento() == null || estudianteModel.getDocumento().trim().isEmpty()) {
            throw new DomainException("El documento es obligatorio");
        }
    }
}
