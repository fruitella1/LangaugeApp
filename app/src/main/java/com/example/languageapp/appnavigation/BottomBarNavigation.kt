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

const val LanguageScr = "LanguageScreen"
const val HomeScr = "HomeScreen"
const val Home = "Home"
const val Search = "Search"
@Composable
fun BottomBarNavigation(navController: NavHostController): Unit {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = Home) },
            label = { Text(Home) },
            selected = currentRoute == HomeScr,
            onClick = { navController.navigate(HomeScr) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Search, contentDescription = Search) },
            label = { Text(Search) },
            selected = currentRoute == LanguageScr,
            onClick = { navController.navigate(LanguageScr) }
        )
    }
}

