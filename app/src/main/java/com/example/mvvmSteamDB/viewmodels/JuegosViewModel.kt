package com.example.mvvmSteamDB.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmSteamDB.components.searchedText
import com.example.mvvmSteamDB.models.ListaJuegos
import com.example.mvvmSteamDB.network.RetrofitClient
import com.example.mvvmSteamDB.viewmodels.interfaces.JuegoChanger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JuegosViewModel: ViewModel(), JuegoChanger {
    private val _juegos = MutableStateFlow<List<ListaJuegos>>(emptyList())
    val juegos = _juegos.asStateFlow()

    init{
        changeJuego()
    }

    override fun changeJuego() {
        obtenerJuegos(searchedText)
    }

    fun obtenerJuegos(busqueda: String){
        viewModelScope.launch(Dispatchers.IO){
            withContext(Dispatchers.Main){
                val response = RetrofitClient.retrofit.Consulta(busqueda)
                _juegos.value = response.body() ?: emptyList()
            }
        }
    }
}