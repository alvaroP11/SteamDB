package com.example.mvvmSteamDB.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mvvmSteamDB.viewmodels.JuegosViewModel
import com.example.mvvmSteamDB.viewmodels.interfaces.JuegoChanger
import com.example.mvvmSteamDB.views.InicioView
import com.example.mvvmSteamDB.views.SobreCheapSharkView

@Composable
fun NavManager(viewModel: JuegosViewModel, changer: JuegoChanger){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Busqueda"
    ){
        composable("Busqueda"){
            InicioView(navController, viewModel, changer)
        }
        //Cada Composable seria una vista nueva
        composable("Sobre CheapShark"){
            SobreCheapSharkView(navController)
        }
    }
}