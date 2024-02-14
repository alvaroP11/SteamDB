package com.example.mvvmSteamDB.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.MVVMSteamDB.R
import com.example.mvvmSteamDB.components.CardJuegos
import com.example.mvvmSteamDB.components.JuegosTopBar
import com.example.mvvmSteamDB.models.NavigationItem
import com.example.mvvmSteamDB.viewmodels.JuegosViewModel
import com.example.mvvmSteamDB.viewmodels.interfaces.JuegoChanger
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioView(navController: NavController, viewModel: JuegosViewModel, changer: JuegoChanger){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable { mutableStateOf(0)}

    val items = listOf(
        NavigationItem(
            title = "Busqueda",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            "Busqueda"
        ),
        NavigationItem(
            title = "Sobre CheapShark",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info,
            "Sobre CheapShark"
        ),
    )

    ModalNavigationDrawer(
        drawerContent = { //opciones dentro del menu
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp))
                items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = { Text(text = item.title)},
                        selected = index == selectedItemIndex,
                        onClick = {
                            navController.navigate(item.route)
                            selectedItemIndex = index
                            scope.launch{
                                drawerState.close()
                            }
                        },
                        icon = {
                            Icon(imageVector = if(index == selectedItemIndex) {
                            item.selectedIcon
                        } else item.unselectedIcon,
                            contentDescription = item.title )
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold (
            topBar = {
                JuegosTopBar(drawerState, scope, changer)
            }
        ) {
            ContenidoInicioView(
                navController = navController,
                viewModel = viewModel,
                pad = it
            )
        }
    }
}

@Composable
fun ContenidoInicioView(
    navController: NavController,
    viewModel: JuegosViewModel,
    pad: PaddingValues
){
    val juegos by viewModel.juegos.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.steam_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp),
            contentPadding = PaddingValues(
                start = 10.dp,
                top = 75.dp,
                end = 10.dp,
                bottom = 16.dp
            ),
            modifier = Modifier.background(Color.Transparent),
            content = {
                items(items = juegos) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CardJuegos(juego = it)
                        Text(
                            text = it.nombre,
                            fontSize = 18.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(start = 12.dp)
                        )
                    }
                }
            }
        )
    }
}