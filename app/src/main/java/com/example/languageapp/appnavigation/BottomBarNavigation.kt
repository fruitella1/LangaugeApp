package com.example.languageapp.appnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBarNavigation(navController: NavHostController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = HOME) },
            label = { Text(HOME) },
            selected = currentRoute == HOME_SCREEN,
            onClick = { navController.navigate(HOME_SCREEN) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Search, contentDescription = SEARCH) },
            label = { Text(SEARCH) },
            selected = currentRoute == LANGUAGE_SCREEN,
            onClick = { navController.navigate(LANGUAGE_SCREEN) }
        )
    }
}

