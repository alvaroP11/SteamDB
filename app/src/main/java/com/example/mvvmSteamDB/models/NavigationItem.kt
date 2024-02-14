package com.example.mvvmSteamDB.models

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
)