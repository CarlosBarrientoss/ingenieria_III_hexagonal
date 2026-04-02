package com.ing_hexagonal.application.mapper;

import com.ing_hexagonal.application.dto.request.EstudianteRequestDto;
import com.ing_hexagonal.application.dto.response.EstudianteResponseDto;
import com.ing_hexagonal.domain.model.EstudianteModel;

import java.util.List;
import java.util.stream.Collectors;

public class EstudianteDtoMapper {

    public static EstudianteModel toModel(EstudianteRequestDto dto) {
        EstudianteModel model = new EstudianteModel();
        model.setNombres(dto.getNombres());
        model.setApellidos(dto.getApellidos());
        model.setCorreo(dto.getCorreo());
        model.setDocumento(dto.getDocumento());
        return model;
    }

    public static EstudianteResponseDto toResponse(EstudianteModel model) {
        EstudianteResponseDto dto = new EstudianteResponseDto();
        dto.setId(model.getId());
        dto.setNombres(model.getNombres());
        dto.setApellidos(model.getApellidos());
        dto.setCorreo(model.getCorreo());
        dto.setDocumento(model.getDocumento());
        return dto;
    }

    public static List<EstudianteResponseDto> toResponseList(List<EstudianteModel> models) {
        return models.stream().map(EstudianteDtoMapper::toResponse).collect(Collectors.toList());
    }
}
