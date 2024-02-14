package com.example.mvvmSteamDB.network

import com.example.mvvmSteamDB.models.ListaJuegos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JuegosAPI {
    //Consulta parametrizada para obtener lo introducido en la barra de busqueda
    @GET("games")
    suspend fun Consulta(@Query("title")juego: String): Response<List<ListaJuegos>>
}