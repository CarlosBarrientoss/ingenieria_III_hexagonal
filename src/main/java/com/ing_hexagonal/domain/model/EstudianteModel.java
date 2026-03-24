package com.ing_hexagonal.domain.model;

import java.util.ArrayList;
import java.util.List;

public class EstudianteModel {

    private Long id;
    private String nombres;
    private String apellidos;
    private String correo;
    private String documento;
    private List<MatriculaModel> matriculas;

    public EstudianteModel() {
        this.matriculas = new ArrayList<>();
    }

    public EstudianteModel(Long id, String nombres, String apellidos, String correo, String documento) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.documento = documento;
        this.matriculas = new ArrayList<>();
    }

    public EstudianteModel(Long id, String nombres, String apellidos, String correo, String documento, List<MatriculaModel> matriculas) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.documento = documento;
        this.matriculas = matriculas != null ? matriculas : new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDocumento() {
        return documento;
    }

    public List<MatriculaModel> getMatriculas() {
        return matriculas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setMatriculas(List<MatriculaModel> matriculas) {
        this.matriculas = matriculas != null ? matriculas : new ArrayList<>();
    }
}


/*
 * EstudianteModel

    Tipo: Modelo de dominio
    Explicación:

        Representa la entidad Estudiante dentro del negocio.

    Responsabilidad:
        Contener datos del estudiante
        Representar reglas del dominio

* Importante:

    * NO debe tener:

      @Entity
      @Column

Solo lógica de negocio
*
* */