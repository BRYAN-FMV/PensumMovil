package com.devproy.pensummovil.Model

data class ClaseResponse(
    val result: List<Clase>
)

data class Clase(
    val id_clase: String,
    val nombre_clase: String,
    val creditos: Int,
    val estado: Boolean,
    val TipoClase: Int
)

