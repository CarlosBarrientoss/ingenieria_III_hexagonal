package com.ing_hexagonal.infrastructure.input.rest;

import com.ing_hexagonal.domain.exception.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * Manejador global de excepciones de la aplicación.
 /*
 * Su función es capturar errores producidos durante la ejecución
 * de los casos de uso o de la infraestructura y transformarlos
 * en respuestas HTTP claras y controladas para el cliente.
 /*
 * En el contexto de seguridad, permite devolver mensajes adecuados
 * ante problemas de autenticación, autorización o validación.
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<String> handleDomainException(DomainException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ocurrió un error interno en el servidor");
    }
}