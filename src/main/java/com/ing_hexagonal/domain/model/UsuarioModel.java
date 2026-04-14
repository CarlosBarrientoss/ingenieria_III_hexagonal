package com.ing_hexagonal.domain.model;

/**
 * Modelo de dominio que representa a un usuario del sistema.
 * Contiene la información esencial para el proceso de autenticación
 * y autorización, como nombre, correo, contraseña, rol y estado.
 * En arquitectura hexagonal, este modelo pertenece al dominio y
 * no depende de frameworks externos. Su propósito es encapsular
 * la información del usuario como parte de la lógica de negocio
 * relacionada con la seguridad.
 */

public class UsuarioModel {

        private Long id;
        private String nombre;
        private String correo;
        private String password;
        private Rol rol;
        private boolean activo;

        public UsuarioModel() {
        }

        public UsuarioModel(Long id, String nombre, String correo, String password, Rol rol, boolean activo) {
            this.id = id;
            this.nombre = nombre;
            this.correo = correo;
            this.password = password;
            this.rol = rol;
            this.activo = activo;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Rol getRol() {
            return rol;
        }

        public void setRol(Rol rol) {
            this.rol = rol;
        }

        public boolean isActivo() {
            return activo;
        }

        public void setActivo(boolean activo) {
            this.activo = activo;
        }
}

