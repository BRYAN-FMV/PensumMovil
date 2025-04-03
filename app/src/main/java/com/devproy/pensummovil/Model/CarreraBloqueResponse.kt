package com.devproy.pensummovil.Model

data class CarreraBloqueResponse (
    val result: List<CarreraCBloque>
)

data class CarreraCBloque(
    val id_ccb: Int,
    val facultadId: String,
    val id_clase: String,
    val id_bloque: Int
)
