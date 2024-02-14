package com.example.mvvmSteamDB.network

import com.example.mvvmSteamDB.utils.Constantes
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val retrofit: JuegosAPI by lazy{
        Retrofit.Builder().baseUrl(Constantes.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(JuegosAPI::class.java)
    }
}