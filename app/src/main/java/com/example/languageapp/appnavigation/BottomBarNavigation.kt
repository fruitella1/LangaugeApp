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
import androidx.navigation.NavController
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
            selected = isRouteSelected(route = currentRoute, prefix = HOME_SCREEN),
            onClick = {
                navBarItemClick(
                    route = HOME_SCREEN,
                    navController = navController
                )
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Search, contentDescription = SEARCH) },
            label = { Text(SEARCH) },
            selected = isRouteSelected(route = currentRoute, prefix = SELECTED_LANGUAGE_SCREEN),
            onClick = {
                navBarItemClick(
                    route = LANGUAGE_SCREEN,
                    navController = navController
                )
            }
        )
    }
}

fun navBarItemClick(route: String, navController: NavController) {
    navController.navigate(route) {
        popUpTo(0) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

private fun isRouteSelected (route: String?, prefix: String): Boolean{
   return route?.startsWith(prefix) == true
}

const val HOME = "Home"
const val SEARCH = "Search"