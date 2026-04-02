package com.ing_hexagonal.infrastructure.output.jpa.adapter;

import com.ing_hexagonal.domain.model.MatriculaModel;
import com.ing_hexagonal.domain.spi.IMatriculaPersistencePort;
import com.ing_hexagonal.infrastructure.output.jpa.entity.EstudianteEntity;
import com.ing_hexagonal.infrastructure.output.jpa.entity.MatriculaEntity;
import com.ing_hexagonal.infrastructure.output.jpa.repository.IEstudianteRepository;
import com.ing_hexagonal.infrastructure.output.jpa.repository.IMatriculaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MatriculaJpaAdapter implements IMatriculaPersistencePort {

    private final IMatriculaRepository matriculaRepository;
    private final IEstudianteRepository estudianteRepository;

    public MatriculaJpaAdapter(IMatriculaRepository matriculaRepository,
                               IEstudianteRepository estudianteRepository) {
        this.matriculaRepository = matriculaRepository;
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    @Transactional
    public void saveMatricula(MatriculaModel matriculaModel) {
        MatriculaEntity entity = toEntity(matriculaModel);
        matriculaRepository.save(entity);
    }

    @Override
    @Transactional
    public void updateMatricula(MatriculaModel matriculaModel, Long id) {
        MatriculaEntity entity = matriculaRepository.findById(id).orElseThrow();
        EstudianteEntity estudianteEntity = estudianteRepository.findById(matriculaModel.getEstudianteId()).orElseThrow();

        entity.setCodigo(matriculaModel.getCodigo());
        entity.setFechaMatricula(matriculaModel.getFechaMatricula());
        entity.setPeriodoAcademico(matriculaModel.getPeriodoAcademico());
        entity.setEstudiante(estudianteEntity);

        matriculaRepository.save(entity);
    }

    @Override
    @Transactional
    public void deleteMatricula(Long id) {
        matriculaRepository.deleteById(id);
    }

    @Override
    public Optional<MatriculaModel> getMatriculaById(Long id) {
        return matriculaRepository.findById(id).map(this::toModel);
    }

    @Override
    public List<MatriculaModel> getAllMatriculas() {
        return matriculaRepository.findAll()
                .stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<MatriculaModel> getMatriculasByEstudianteId(Long estudianteId) {
        return matriculaRepository.findByEstudiante_Id(estudianteId)
                .stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByCodigo(String codigo) {
        return matriculaRepository.existsByCodigo(codigo);
    }

    @Override
    public boolean existsById(Long id) {
        return matriculaRepository.existsById(id);
    }

    private MatriculaEntity toEntity(MatriculaModel model) {
        EstudianteEntity estudianteEntity = estudianteRepository.findById(model.getEstudianteId()).orElseThrow();

        MatriculaEntity entity = new MatriculaEntity();
        entity.setId(model.getId());
        entity.setCodigo(model.getCodigo());
        entity.setFechaMatricula(model.getFechaMatricula());
        entity.setPeriodoAcademico(model.getPeriodoAcademico());
        entity.setEstudiante(estudianteEntity);
        return entity;
    }

    private MatriculaModel toModel(MatriculaEntity entity) {
        MatriculaModel model = new MatriculaModel();
        model.setId(entity.getId());
        model.setCodigo(entity.getCodigo());
        model.setFechaMatricula(entity.getFechaMatricula());
        model.setPeriodoAcademico(entity.getPeriodoAcademico());
        model.setEstudianteId(entity.getEstudiante().getId());
        return model;
    }
}
