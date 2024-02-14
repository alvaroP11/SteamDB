package com.example.mvvmSteamDB

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.mvvmSteamDB.navigation.NavManager
import com.example.mvvmSteamDB.ui.theme.MVVMSteamDBTheme
import com.example.mvvmSteamDB.viewmodels.JuegosViewModel
import com.example.mvvmSteamDB.viewmodels.interfaces.JuegoChanger

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: JuegosViewModel by viewModels()
        
        setContent {
            MVVMSteamDBTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val changer: JuegoChanger = viewModel
                    NavManager(viewModel = viewModel, changer)
                }
            }
        }
    }
}

