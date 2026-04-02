package com.ing_hexagonal.application.dto.response;

import java.time.LocalDate;

public class MatriculaResponseDto {

    private Long id;
    private String codigo;
    private LocalDate fechaMatricula;
    private String periodoAcademico;
    private Long estudianteId;

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDate getFechaMatricula() {
        return fechaMatricula;
    }

    public String getPeriodoAcademico() {
        return periodoAcademico;
    }

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setFechaMatricula(LocalDate fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public void setPeriodoAcademico(String periodoAcademico) {
        this.periodoAcademico = periodoAcademico;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }
}
