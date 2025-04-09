package com.devproy.pensummovil.ApiService

import com.devproy.pensummovil.Model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.Query

interface PensumApiService {
    //CLASES

    //obtener Clases
    @GET ("api/Clases/getClases")
     suspend fun obtenerClase(): ClaseResponse

     //ALUMNO

     //obtenerAlumno
     @GET("api/Alumno/getAlumno")
     suspend fun obtenerAlumno(): AlumnoResponse

     //CARRERA CLASE BLOQUE

     //obtnerCarreraClaseBloque
     @GET("api/CarreraCM/getCarreraCM")
     suspend fun obtenerCarreraCBloque(): CarreraBloqueResponse

     //APROBADO

     //ObtenerAprobado
     @GET("api/Aprobado/getAprobadoAlumno")
     suspend fun obtenerAprobado(
     @Query("alumnoId") alumnoId: String
     ): Response<AprobadoResponse>


     //Enviar datos
     @POST("api/Aprobado/insertAprobado")
     suspend fun insertAprobado(@Body request: Aprobado): Response<Unit>

     //eliminar datos
     @HTTP(method = "DELETE", path = "api/Aprobado/deleteAprobado", hasBody = true)
     suspend fun deleteAprobado(@Body body: Map<String, String>): Response<Unit>

     //USUARIO
     //Enviar datos de user
     @POST("api/User/signIn") // Ruta basada en tu API
     suspend fun login(@Body user: UserResponse): Response<LoginResponse>


}