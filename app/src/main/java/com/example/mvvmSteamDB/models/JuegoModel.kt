package com.example.mvvmSteamDB.models

import com.google.gson.annotations.SerializedName

data class ListaJuegos(
    @SerializedName("external")
    val nombre: String,
    @SerializedName("thumb")
    val imagen: String
)