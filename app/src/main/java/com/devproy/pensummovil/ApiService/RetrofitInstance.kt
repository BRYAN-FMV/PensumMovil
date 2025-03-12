package com.devproy.pensummovil.ApiService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance{
    private const val BASE_URL = "https//localhost:3011/"

    val api: PensumApiService by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PensumApiService::class.java)
    }
}