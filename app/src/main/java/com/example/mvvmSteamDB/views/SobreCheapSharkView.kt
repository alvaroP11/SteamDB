package com.example.mvvmSteamDB.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mvvmSteamDB.components.MainTopBar
import com.example.mvvmSteamDB.models.NavigationItem
import kotlinx.coroutines.launch
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.MVVMSteamDB.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SobreCheapSharkView(navController: NavController){

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

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
        drawerContent = {
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
                            }else item.unselectedIcon,
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
                MainTopBar(titulo = "SteamDB App", drawerState, scope)
            }
        ){
            ContenidoInfoView(
                navController = navController,
                pad = it
            )
        }
    }
}

@Composable
fun ContenidoInfoView(navController: NavController, pad: PaddingValues){
    Column(
        modifier = Modifier.fillMaxWidth(),
        Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = navController.context.getString(R.string.CheapShark),
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black,
            modifier = Modifier.padding(top = 100.dp, bottom = 20.dp),
            fontSize = 20.sp
        )

        Image(painter = painterResource(id = R.drawable.cheapshark_logo),
            contentDescription = "Logo",
            modifier = Modifier.fillMaxWidth()
        )

        Text(text = navController.context.getString(R.string.CheapSharkInfo),
            textAlign =TextAlign.Justify,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier.padding(20.dp)
        )
    }
}