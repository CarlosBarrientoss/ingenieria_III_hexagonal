package com.ing_hexagonal.domain.exception;

/*
 * Excepción de dominio que representa un error de autenticación.
 * Se utiliza cuando las credenciales suministradas por el usuario
 * no coinciden con la información registrada en el sistema.
 * Esta excepción permite expresar de forma clara una violación
 * a las reglas del proceso de inicio de sesión.
 */
public class CredencialesInvalidasException extends RuntimeException {
    public CredencialesInvalidasException(String message) {
        super(message);
    }
}
