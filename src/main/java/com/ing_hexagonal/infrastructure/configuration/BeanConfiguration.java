package com.ing_hexagonal.infrastructure.configuration;

import com.ing_hexagonal.domain.api.IEstudianteServicePort;
import com.ing_hexagonal.domain.api.IMatriculaServicePort;
import com.ing_hexagonal.domain.spi.IEstudiantePersistencePort;
import com.ing_hexagonal.domain.spi.IMatriculaPersistencePort;
import com.ing_hexagonal.domain.usecase.EstudianteUseCase;
import com.ing_hexagonal.domain.usecase.MatriculaUseCase;
import com.ing_hexagonal.infrastructure.output.jpa.adapter.EstudianteJpaAdapter;
import com.ing_hexagonal.infrastructure.output.jpa.adapter.MatriculaJpaAdapter;
import com.ing_hexagonal.infrastructure.output.jpa.repository.IEstudianteRepository;
import com.ing_hexagonal.infrastructure.output.jpa.repository.IMatriculaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración encargada de registrar los casos de uso
 * y ensamblar las dependencias entre puertos y adaptadores.
 /*
 * Su función dentro de la arquitectura hexagonal es conectar
 * el dominio con las implementaciones de infraestructura
 * sin que el núcleo del sistema dependa de Spring directamente.
 /*
 * En el módulo de seguridad, permite instanciar el caso de uso
 * de autenticación con sus respectivas dependencias.
 */

@Configuration
public class BeanConfiguration {

    @Bean
    public IEstudiantePersistencePort estudiantePersistencePort(IEstudianteRepository estudianteRepository) {
        return new EstudianteJpaAdapter(estudianteRepository);
    }

    @Bean
    public IMatriculaPersistencePort matriculaPersistencePort(IMatriculaRepository matriculaRepository,
                                                              IEstudianteRepository estudianteRepository) {
        return new MatriculaJpaAdapter(matriculaRepository, estudianteRepository);
    }

    @Bean
    public IEstudianteServicePort estudianteServicePort(IEstudiantePersistencePort estudiantePersistencePort,
                                                        IMatriculaPersistencePort matriculaPersistencePort) {
        return new EstudianteUseCase(estudiantePersistencePort, matriculaPersistencePort);
    }

    @Bean
    public IMatriculaServicePort matriculaServicePort(IMatriculaPersistencePort matriculaPersistencePort,
                                                      IEstudiantePersistencePort estudiantePersistencePort) {
        return new MatriculaUseCase(matriculaPersistencePort, estudiantePersistencePort);
    }
}