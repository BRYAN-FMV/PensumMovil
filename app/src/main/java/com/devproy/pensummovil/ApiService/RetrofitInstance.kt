package com.devproy.pensummovil.ApiService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance{
    //private const val BASE_URL = "http://10.3.61.180:3010" //UNICAH
    private const val BASE_URL = "http://192.168.0.10:3010" //Casa Bryan

    val api: PensumApiService by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PensumApiService::class.java)
    }
}