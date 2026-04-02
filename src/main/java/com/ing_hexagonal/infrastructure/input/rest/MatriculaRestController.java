package com.ing_hexagonal.infrastructure.input.rest;

import com.ing_hexagonal.application.dto.request.MatriculaRequestDto;
import com.ing_hexagonal.application.dto.response.MatriculaResponseDto;
import com.ing_hexagonal.application.mapper.MatriculaDtoMapper;
import com.ing_hexagonal.domain.api.IMatriculaServicePort;
import com.ing_hexagonal.domain.model.MatriculaModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaRestController {

    private final IMatriculaServicePort matriculaServicePort;

    public MatriculaRestController(IMatriculaServicePort matriculaServicePort) {
        this.matriculaServicePort = matriculaServicePort;
    }

    @PostMapping
    public ResponseEntity<String> saveMatricula(@RequestBody MatriculaRequestDto requestDto) {
        MatriculaModel model = MatriculaDtoMapper.toModel(requestDto);
        matriculaServicePort.saveMatricula(model);
        return ResponseEntity.status(HttpStatus.CREATED).body("Matrícula creada correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMatricula(@RequestBody MatriculaRequestDto requestDto,
                                                  @PathVariable Long id) {
        MatriculaModel model = MatriculaDtoMapper.toModel(requestDto);
        matriculaServicePort.updateMatricula(model, id);
        return ResponseEntity.ok("Matrícula actualizada correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMatricula(@PathVariable Long id) {
        matriculaServicePort.deleteMatricula(id);
        return ResponseEntity.ok("Matrícula eliminada correctamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaResponseDto> getMatriculaById(@PathVariable Long id) {
        MatriculaModel model = matriculaServicePort.getMatriculaById(id);
        return ResponseEntity.ok(MatriculaDtoMapper.toResponse(model));
    }

    @GetMapping
    public ResponseEntity<List<MatriculaResponseDto>> getAllMatriculas() {
        return ResponseEntity.ok(
                MatriculaDtoMapper.toResponseList(matriculaServicePort.getAllMatriculas())
        );
    }

    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<List<MatriculaResponseDto>> getByEstudiante(@PathVariable Long estudianteId) {
        return ResponseEntity.ok(
                MatriculaDtoMapper.toResponseList(matriculaServicePort.getMatriculasByEstudianteId(estudianteId))
        );
    }
}
