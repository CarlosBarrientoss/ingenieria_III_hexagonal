package com.ing_hexagonal.infrastructure.output.jpa.repository;


import com.ing_hexagonal.infrastructure.output.jpa.entity.MatriculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMatriculaRepository extends JpaRepository<MatriculaEntity, Long> {
    boolean existsByCodigo(String codigo);
    List<MatriculaEntity> findByEstudiante_Id(Long estudianteId);
}
