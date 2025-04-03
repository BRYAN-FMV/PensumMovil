package com.devproy.pensummovil.Model

data class AprobadoResponse(
   val result: List<Aprobado>
)

data class Aprobado(
    val id_aprobado: Int,
    val alumnoId: String,
    val id_clase: String,
    val nota_final: String
)