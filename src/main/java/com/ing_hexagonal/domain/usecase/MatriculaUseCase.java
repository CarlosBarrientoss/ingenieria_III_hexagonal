package com.ing_hexagonal.domain.usecase;

import com.ing_hexagonal.domain.api.IMatriculaServicePort;
import com.ing_hexagonal.domain.exception.DomainException;
import com.ing_hexagonal.domain.model.MatriculaModel;
import com.ing_hexagonal.domain.spi.IEstudiantePersistencePort;
import com.ing_hexagonal.domain.spi.IMatriculaPersistencePort;

import java.time.LocalDate;
import java.util.List;

public class MatriculaUseCase implements IMatriculaServicePort {

    private final IMatriculaPersistencePort matriculaPersistencePort;
    private final IEstudiantePersistencePort estudiantePersistencePort;

    public MatriculaUseCase(IMatriculaPersistencePort matriculaPersistencePort,
                            IEstudiantePersistencePort estudiantePersistencePort) {
        this.matriculaPersistencePort = matriculaPersistencePort;
        this.estudiantePersistencePort = estudiantePersistencePort;
    }

    @Override
    public void saveMatricula(MatriculaModel matriculaModel) {
        validateMatricula(matriculaModel);

        if (!estudiantePersistencePort.existsById(matriculaModel.getEstudianteId())) {
            throw new DomainException("No se puede registrar la matrícula porque el estudiante no existe");
        }

        if (matriculaPersistencePort.existsByCodigo(matriculaModel.getCodigo())) {
            throw new DomainException("Ya existe una matrícula con ese código");
        }

        matriculaPersistencePort.saveMatricula(matriculaModel);
    }

    @Override
    public void updateMatricula(MatriculaModel matriculaModel, Long id) {
        if (id == null) {
            throw new DomainException("El id de la matrícula es obligatorio");
        }

        if (!matriculaPersistencePort.existsById(id)) {
            throw new DomainException("La matrícula no existe");
        }

        validateMatricula(matriculaModel);

        if (!estudiantePersistencePort.existsById(matriculaModel.getEstudianteId())) {
            throw new DomainException("El estudiante asociado no existe");
        }

        matriculaPersistencePort.updateMatricula(matriculaModel, id);
    }

    @Override
    public void deleteMatricula(Long id) {
        if (id == null) {
            throw new DomainException("El id de la matrícula es obligatorio");
        }

        if (!matriculaPersistencePort.existsById(id)) {
            throw new DomainException("La matrícula no existe");
        }

        matriculaPersistencePort.deleteMatricula(id);
    }

    @Override
    public MatriculaModel getMatriculaById(Long id) {
        if (id == null) {
            throw new DomainException("El id de la matrícula es obligatorio");
        }

        return matriculaPersistencePort.getMatriculaById(id)
                .orElseThrow(() -> new DomainException("Matrícula no encontrada"));
    }

    @Override
    public List<MatriculaModel> getAllMatriculas() {
        return matriculaPersistencePort.getAllMatriculas();
    }

    @Override
    public List<MatriculaModel> getMatriculasByEstudianteId(Long estudianteId) {
        if (estudianteId == null) {
            throw new DomainException("El id del estudiante es obligatorio");
        }

        return matriculaPersistencePort.getMatriculasByEstudianteId(estudianteId);
    }

    private void validateMatricula(MatriculaModel matriculaModel) {
        if (matriculaModel == null) {
            throw new DomainException("La matrícula no puede ser nula");
        }

        if (matriculaModel.getCodigo() == null || matriculaModel.getCodigo().trim().isEmpty()) {
            throw new DomainException("El código de matrícula es obligatorio");
        }

        if (matriculaModel.getPeriodoAcademico() == null || matriculaModel.getPeriodoAcademico().trim().isEmpty()) {
            throw new DomainException("El periodo académico es obligatorio");
        }

        if (matriculaModel.getFechaMatricula() == null) {
            throw new DomainException("La fecha de matrícula es obligatoria");
        }

        if (matriculaModel.getFechaMatricula().isAfter(LocalDate.now())) {
            throw new DomainException("La fecha de matrícula no puede ser futura");
        }

        if (matriculaModel.getEstudianteId() == null) {
            throw new DomainException("El estudiante asociado es obligatorio");
        }
    }
}
