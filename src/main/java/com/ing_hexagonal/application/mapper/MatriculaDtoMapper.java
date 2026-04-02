package com.ing_hexagonal.application.mapper;

import com.ing_hexagonal.application.dto.request.MatriculaRequestDto;
import com.ing_hexagonal.application.dto.response.MatriculaResponseDto;
import com.ing_hexagonal.domain.model.MatriculaModel;

import java.util.List;
import java.util.stream.Collectors;

public class MatriculaDtoMapper {

    public static MatriculaModel toModel(MatriculaRequestDto dto) {
        MatriculaModel model = new MatriculaModel();
        model.setCodigo(dto.getCodigo());
        model.setFechaMatricula(dto.getFechaMatricula());
        model.setPeriodoAcademico(dto.getPeriodoAcademico());
        model.setEstudianteId(dto.getEstudianteId());
        return model;
    }

    public static MatriculaResponseDto toResponse(MatriculaModel model) {
        MatriculaResponseDto dto = new MatriculaResponseDto();
        dto.setId(model.getId());
        dto.setCodigo(model.getCodigo());
        dto.setFechaMatricula(model.getFechaMatricula());
        dto.setPeriodoAcademico(model.getPeriodoAcademico());
        dto.setEstudianteId(model.getEstudianteId());
        return dto;
    }

    public static List<MatriculaResponseDto> toResponseList(List<MatriculaModel> models) {
        return models.stream().map(MatriculaDtoMapper::toResponse).collect(Collectors.toList());
    }
}
