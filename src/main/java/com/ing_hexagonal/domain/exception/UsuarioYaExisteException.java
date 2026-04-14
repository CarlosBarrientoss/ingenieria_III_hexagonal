package com.ing_hexagonal.domain.exception;

/**
 * Excepción de dominio que se lanza cuando se intenta registrar
 * un usuario con un correo ya existente en el sistema.
 * Su función es proteger la integridad de la información y comunicar
 * una regla de negocio relacionada con el proceso de registro.
 */
public class UsuarioYaExisteException extends RuntimeException {
    public UsuarioYaExisteException(String message) {
        super(message);
    }
}
