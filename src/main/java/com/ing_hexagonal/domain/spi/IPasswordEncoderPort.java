package com.ing_hexagonal.domain.spi;

/*
 * Puerto de salida que abstrae la codificación y verificación de contraseñas.
 * Su función es evitar que el dominio dependa directamente de Spring Security
 * o de una implementación concreta de cifrado como BCrypt.
 * Gracias a esta interfaz, la lógica de negocio puede trabajar con contraseñas
 * de forma segura manteniendo el desacoplamiento propio de la arquitectura hexagonal.
 */
public interface IPasswordEncoderPort {


}
