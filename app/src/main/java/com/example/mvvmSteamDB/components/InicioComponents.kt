package com.example.mvvmSteamDB.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.MVVMSteamDB.R
import com.example.mvvmSteamDB.models.ListaJuegos
import com.example.mvvmSteamDB.viewmodels.interfaces.JuegoChanger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//Variable para la consulta
var searchedText = ""

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(titulo: String, drawerState: DrawerState, scope: CoroutineScope){
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = titulo)
            }
        },
        //Icono para desplegar el nav
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                 }
            ) {
                Icon(Icons.Filled.Menu, contentDescription = "Navigation Icon")
            }
        },

        // Icono
        actions = {
            IconButton(onClick = { /* Handle navigation icon click */ }) {
                Image(
                    painterResource(id = R.drawable.steam_logo),
                    null,
                    modifier = Modifier
                        .height(30.dp)
                        .padding(2.dp)
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.White
        )
    )
}

//Topbar para la pagina de los juegos
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JuegosTopBar(drawerState: DrawerState, scope: CoroutineScope, changer: JuegoChanger){
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                val textState = remember {
                    mutableStateOf(TextFieldValue(""))
                }
                SearchView(state = textState, placeholder = "Busque un juego", modifier = Modifier)

                searchedText = textState.value.text
                changer.changeJuego()
            }
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }
            ) {
                Icon(Icons.Filled.Menu, contentDescription = "Navigation Icon")
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.White
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    state: MutableState<TextFieldValue>,
    placeholder: String,
    modifier: Modifier.Companion
) {
    TextField(
        value = state.value,
        onValueChange = {value->
            state.value = value
        },
        modifier
            .clip(RoundedCornerShape(30.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(30.dp)),

        placeholder = {
            Text(text = placeholder)
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White
        ),
        maxLines = 1,
        singleLine = true,
        textStyle = TextStyle(
            color = Color.Black, fontSize = 20.sp
        )
    )
}

@Composable
fun CardJuegos(
    juego: ListaJuegos
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(15.dp)
            .shadow(40.dp)
            .background(Color.Transparent)
    ){
        Column{
            InicioImagen(imagen = juego.imagen)
        }
    }
}

@Composable
fun InicioImagen(imagen:String){
    val imagen = rememberImagePainter(data = imagen)
    Image(
        modifier = Modifier
            .height(80.dp)
            .width(200.dp)
            .background(Color.Transparent),
        painter = imagen,
        contentDescription = null,
        contentScale = ContentScale.Fit,
    )
}


