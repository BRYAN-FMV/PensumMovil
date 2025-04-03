package com.devproy.pensummovil.Model

data class AlumnoResponse (
    val result: List<Alumno>
)
data class Alumno(
    val alumnoId: String,
    val nombre: String,
    val facultadId: String,
    val email: String
)