package com.ing_hexagonal.domain.spi;

/*
 * Puerto de salida del dominio que define las operaciones necesarias
 * para persistir y consultar usuarios en un mecanismo de almacenamiento.
 * En el contexto de seguridad, esta interfaz permite buscar usuarios,
 * verificar si existen y almacenarlos, sin que el dominio conozca
 * si los datos se guardan en MySQL, PostgreSQL, MongoDB u otro medio.
 */
public interface IUsuarioPersistencePort {


}
