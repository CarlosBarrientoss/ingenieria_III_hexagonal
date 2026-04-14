package com.ing_hexagonal.domain.usecase;

import com.ing_hexagonal.domain.exception.DomainException;
import com.ing_hexagonal.domain.model.EstudianteModel;
import com.ing_hexagonal.domain.spi.IEstudiantePersistencePort;
import com.ing_hexagonal.domain.spi.IMatriculaPersistencePort;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstudianteUseCaseTest {

    @Mock
    private IEstudiantePersistencePort estudiantePersistencePort;

    @Mock
    private IMatriculaPersistencePort matriculaPersistencePort;

    @InjectMocks
    private EstudianteUseCase estudianteUseCase;

    private EstudianteModel estudianteModel;

    @BeforeEach
    void setUp() {
        estudianteModel = new EstudianteModel();
        estudianteModel.setId(1L);
        estudianteModel.setNombres("Luciana");
        estudianteModel.setApellidos("Suarez");
        estudianteModel.setCorreo("luciana@correo.com");
        estudianteModel.setDocumento("12345678");
    }

    @Test
    void saveEstudiante_ShouldSave_WhenDataIsValid() {
        when(estudiantePersistencePort.existsByCorreo(estudianteModel.getCorreo())).thenReturn(false);
        when(estudiantePersistencePort.existsByDocumento(estudianteModel.getDocumento())).thenReturn(false);

        estudianteUseCase.saveEstudiante(estudianteModel);

        verify(estudiantePersistencePort, times(1)).saveEstudiante(estudianteModel);
    }

    @Test
    void saveEstudiante_ShouldThrowException_WhenCorreoAlreadyExists() {
        when(estudiantePersistencePort.existsByCorreo(estudianteModel.getCorreo())).thenReturn(true);

        DomainException exception = assertThrows(
                DomainException.class,
                () -> estudianteUseCase.saveEstudiante(estudianteModel)
        );

        assertEquals("Ya existe un estudiante con ese correo", exception.getMessage());
        verify(estudiantePersistencePort, never()).saveEstudiante(any());
    }

    @Test
    void saveEstudiante_ShouldThrowException_WhenDocumentoAlreadyExists() {
        when(estudiantePersistencePort.existsByCorreo(estudianteModel.getCorreo())).thenReturn(false);
        when(estudiantePersistencePort.existsByDocumento(estudianteModel.getDocumento())).thenReturn(true);

        DomainException exception = assertThrows(
                DomainException.class,
                () -> estudianteUseCase.saveEstudiante(estudianteModel)
        );

        assertEquals("Ya existe un estudiante con ese documento", exception.getMessage());
        verify(estudiantePersistencePort, never()).saveEstudiante(any());
    }

    @Test
    void saveEstudiante_ShouldThrowException_WhenNombresIsEmpty() {
        estudianteModel.setNombres(" ");

        DomainException exception = assertThrows(
                DomainException.class,
                () -> estudianteUseCase.saveEstudiante(estudianteModel)
        );

        assertEquals("Los nombres son obligatorios", exception.getMessage());
        verify(estudiantePersistencePort, never()).saveEstudiante(any());
    }

    @Test
    void deleteEstudiante_ShouldDelete_WhenStudentExistsAndHasNoMatriculas() {
        Long id = 1L;

        when(estudiantePersistencePort.existsById(id)).thenReturn(true);
        when(matriculaPersistencePort.getMatriculasByEstudianteId(id)).thenReturn(Collections.emptyList());

        estudianteUseCase.deleteEstudiante(id);

        verify(estudiantePersistencePort, times(1)).deleteEstudiante(id);
    }

    @Test
    void deleteEstudiante_ShouldThrowException_WhenStudentHasMatriculas() {
        Long id = 1L;

        when(estudiantePersistencePort.existsById(id)).thenReturn(true);
        when(matriculaPersistencePort.getMatriculasByEstudianteId(id)).thenReturn(Collections.singletonList(mock()));

        DomainException exception = assertThrows(
                DomainException.class,
                () -> estudianteUseCase.deleteEstudiante(id)
        );

        assertEquals("No se puede eliminar el estudiante porque tiene matrículas asociadas", exception.getMessage());
        verify(estudiantePersistencePort, never()).deleteEstudiante(anyLong());
    }

    @Test
    void getEstudianteById_ShouldReturnEstudiante_WhenExists() {
        Long id = 1L;

        when(estudiantePersistencePort.getEstudianteById(id)).thenReturn(java.util.Optional.of(estudianteModel));

        EstudianteModel result = estudianteUseCase.getEstudianteById(id);

        assertNotNull(result);
        assertEquals("Luciana", result.getNombres());
        assertEquals("luciana@correo.com", result.getCorreo());
        verify(estudiantePersistencePort, times(1)).getEstudianteById(id);
    }

    @Test
    void getEstudianteById_ShouldThrowException_WhenNotExists() {
        Long id = 1L;

        when(estudiantePersistencePort.getEstudianteById(id)).thenReturn(java.util.Optional.empty());

        DomainException exception = assertThrows(
                DomainException.class,
                () -> estudianteUseCase.getEstudianteById(id)
        );

        assertEquals("Estudiante no encontrado", exception.getMessage());
        verify(estudiantePersistencePort, times(1)).getEstudianteById(id);
    }

    @Test
    void getEstudianteById_ShouldThrowException_WhenIdIsNull() {
        DomainException exception = assertThrows(
                DomainException.class,
                () -> estudianteUseCase.getEstudianteById(null)
        );

        assertEquals("El id del estudiante es obligatorio", exception.getMessage());
        verify(estudiantePersistencePort, never()).getEstudianteById(any());
    }

    @Test
    void updateEstudiante_ShouldUpdate_WhenDataIsValidAndStudentExists() {
        Long id = 1L;

        when(estudiantePersistencePort.existsById(id)).thenReturn(true);

        estudianteUseCase.updateEstudiante(estudianteModel, id);

        verify(estudiantePersistencePort, times(1)).updateEstudiante(estudianteModel, id);
    }

    @Test
    void updateEstudiante_ShouldThrowException_WhenIdIsNull() {
        DomainException exception = assertThrows(
                DomainException.class,
                () -> estudianteUseCase.updateEstudiante(estudianteModel, null)
        );

        assertEquals("El id del estudiante es obligatorio", exception.getMessage());
        verify(estudiantePersistencePort, never()).updateEstudiante(any(), any());
    }

    @Test
    void updateEstudiante_ShouldThrowException_WhenStudentDoesNotExist() {
        Long id = 1L;

        when(estudiantePersistencePort.existsById(id)).thenReturn(false);

        DomainException exception = assertThrows(
                DomainException.class,
                () -> estudianteUseCase.updateEstudiante(estudianteModel, id)
        );

        assertEquals("El estudiante no existe", exception.getMessage());
        verify(estudiantePersistencePort, never()).updateEstudiante(any(), anyLong());
    }

    @Test
    void getAllEstudiantes_ShouldReturnList() {
        java.util.List<EstudianteModel> estudiantes = java.util.List.of(estudianteModel);

        when(estudiantePersistencePort.getAllEstudiantes()).thenReturn(estudiantes);

        java.util.List<EstudianteModel> result = estudianteUseCase.getAllEstudiantes();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Luciana", result.get(0).getNombres());
        verify(estudiantePersistencePort, times(1)).getAllEstudiantes();
    }

    @Test
    void saveEstudiante_ShouldThrowException_WhenCorreoFormatIsInvalid() {
        estudianteModel.setCorreo("correo-invalido");

        DomainException exception = assertThrows(
                DomainException.class,
                () -> estudianteUseCase.saveEstudiante(estudianteModel)
        );

        assertEquals("El correo no tiene un formato válido", exception.getMessage());
        verify(estudiantePersistencePort, never()).saveEstudiante(any());
    }


}
