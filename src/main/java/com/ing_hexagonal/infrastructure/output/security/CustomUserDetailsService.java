package com.ing_hexagonal.infrastructure.output.security;


/**
 * Servicio de infraestructura requerido por Spring Security
 * para cargar la información de un usuario durante el proceso
 * de autenticación.
 /*
 * Su responsabilidad es buscar al usuario en la base de datos
 * y convertirlo en un objeto UserDetails reconocido por Spring Security,
 * incluyendo credenciales, estado y roles asignados.
 */

public class CustomUserDetailsService {
}
