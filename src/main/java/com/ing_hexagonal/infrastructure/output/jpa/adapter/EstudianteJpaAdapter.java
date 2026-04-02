package com.ing_hexagonal.infrastructure.output.jpa.adapter;

import com.ing_hexagonal.domain.model.EstudianteModel;
import com.ing_hexagonal.domain.spi.IEstudiantePersistencePort;
import com.ing_hexagonal.infrastructure.output.jpa.entity.EstudianteEntity;
import com.ing_hexagonal.infrastructure.output.jpa.repository.IEstudianteRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EstudianteJpaAdapter implements IEstudiantePersistencePort {

    private final IEstudianteRepository estudianteRepository;

    public EstudianteJpaAdapter(IEstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    @Transactional
    public void saveEstudiante(EstudianteModel estudianteModel) {
        EstudianteEntity entity = toEntity(estudianteModel);
        estudianteRepository.save(entity);
    }

    @Override
    @Transactional
    public void updateEstudiante(EstudianteModel estudianteModel, Long id) {
        EstudianteEntity entity = estudianteRepository.findById(id).orElseThrow();
        entity.setNombres(estudianteModel.getNombres());
        entity.setApellidos(estudianteModel.getApellidos());
        entity.setCorreo(estudianteModel.getCorreo());
        entity.setDocumento(estudianteModel.getDocumento());
        estudianteRepository.save(entity);
    }

    @Override
    @Transactional
    public void deleteEstudiante(Long id) {
        estudianteRepository.deleteById(id);
    }

    @Override
    public Optional<EstudianteModel> getEstudianteById(Long id) {
        return estudianteRepository.findById(id).map(this::toModel);
    }

    @Override
    public List<EstudianteModel> getAllEstudiantes() {
        return estudianteRepository.findAll()
                .stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByCorreo(String correo) {
        return estudianteRepository.existsByCorreo(correo);
    }

    @Override
    public boolean existsByDocumento(String documento) {
        return estudianteRepository.existsByDocumento(documento);
    }

    @Override
    public boolean existsById(Long id) {
        return estudianteRepository.existsById(id);
    }

    private EstudianteEntity toEntity(EstudianteModel model) {
        EstudianteEntity entity = new EstudianteEntity();
        entity.setId(model.getId());
        entity.setNombres(model.getNombres());
        entity.setApellidos(model.getApellidos());
        entity.setCorreo(model.getCorreo());
        entity.setDocumento(model.getDocumento());
        return entity;
    }

    private EstudianteModel toModel(EstudianteEntity entity) {
        EstudianteModel model = new EstudianteModel();
        model.setId(entity.getId());
        model.setNombres(entity.getNombres());
        model.setApellidos(entity.getApellidos());
        model.setCorreo(entity.getCorreo());
        model.setDocumento(entity.getDocumento());
        return model;
    }
}
