package com.devproy.pensummovil.ApiService

import com.devproy.pensummovil.Model.ClaseResponse
import retrofit2.Call
import retrofit2.http.GET

interface PensumApiService {
    //obtener Clases
    @GET ("api/Clases/getClases")
     suspend fun obtenerClase(): ClaseResponse
}