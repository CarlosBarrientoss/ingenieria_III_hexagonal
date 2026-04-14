package com.ing_hexagonal.domain.model;
/**
 * Enumeración que define los roles de seguridad del sistema.
 * Su función es representar los tipos de usuario autorizados
 * dentro de la aplicación, por ejemplo ADMIN, DOCENTE o ESTUDIANTE.
 * Dentro del ecosistema de Spring Security, este archivo permite
 * asociar permisos y restringir el acceso a endpoints según el rol
 * asignado a cada usuario autenticado.
 */
public enum Rol {
    ADMIN,
    DOCENTE,
    ESTUDIANTE
}
