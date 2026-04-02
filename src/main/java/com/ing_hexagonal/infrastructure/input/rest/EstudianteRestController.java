package com.ing_hexagonal.infrastructure.input.rest;

import com.ing_hexagonal.application.dto.request.EstudianteRequestDto;
import com.ing_hexagonal.application.dto.response.EstudianteResponseDto;
import com.ing_hexagonal.application.mapper.EstudianteDtoMapper;
import com.ing_hexagonal.domain.api.IEstudianteServicePort;
import com.ing_hexagonal.domain.model.EstudianteModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteRestController {

    private final IEstudianteServicePort estudianteServicePort;

    public EstudianteRestController(IEstudianteServicePort estudianteServicePort) {
        this.estudianteServicePort = estudianteServicePort;
    }

    @PostMapping
    public ResponseEntity<String> saveEstudiante(@RequestBody EstudianteRequestDto requestDto) {
        EstudianteModel model = EstudianteDtoMapper.toModel(requestDto);
        estudianteServicePort.saveEstudiante(model);
        return ResponseEntity.status(HttpStatus.CREATED).body("Estudiante creado correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEstudiante(@RequestBody EstudianteRequestDto requestDto,
                                                   @PathVariable Long id) {
        EstudianteModel model = EstudianteDtoMapper.toModel(requestDto);
        estudianteServicePort.updateEstudiante(model, id);
        return ResponseEntity.ok("Estudiante actualizado correctamente");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEstudiante(@PathVariable Long id) {
        estudianteServicePort.deleteEstudiante(id);
        return ResponseEntity.ok("Estudiante eliminado correctamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteResponseDto> getEstudianteById(@PathVariable Long id) {
        EstudianteModel model = estudianteServicePort.getEstudianteById(id);
        return ResponseEntity.ok(EstudianteDtoMapper.toResponse(model));
    }

    @GetMapping
    public ResponseEntity<List<EstudianteResponseDto>> getAllEstudiantes() {
        return ResponseEntity.ok(
                EstudianteDtoMapper.toResponseList(estudianteServicePort.getAllEstudiantes())
        );
    }
}
