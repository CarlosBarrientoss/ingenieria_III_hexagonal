package com.ing_hexagonal.infrastructure.output.security;

/**
 * Filtro de seguridad que intercepta cada petición HTTP
 * para verificar si contiene un token JWT válido.
 /*
 * Su función es extraer el token del encabezado Authorization,
 * validarlo y, si es correcto, registrar la autenticación
 * del usuario en el contexto de seguridad de Spring.
 /*
 * Este archivo es clave para implementar autenticación stateless
 * basada en tokens dentro de la aplicación.
 */
public class JwtAuthenticationFilter {
}
