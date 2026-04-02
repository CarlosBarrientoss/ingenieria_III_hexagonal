package com.ing_hexagonal.infrastructure.output.jpa.repository;


import com.ing_hexagonal.infrastructure.output.jpa.entity.EstudianteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstudianteRepository extends JpaRepository<EstudianteEntity, Long> {
    boolean existsByCorreo(String correo);
    boolean existsByDocumento(String documento);
}
