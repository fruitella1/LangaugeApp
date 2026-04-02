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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBarNavigation(navController: NavHostController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        @Composable
        fun NavBarItemCreator(
            route: String,
            currentRoute: String?,
            icon: ImageVector,
            label: String,
            navController: NavController,
            isSelected: (String?) -> Boolean
        ) {
                NavigationBarItem(
                    icon = { Icon(icon, contentDescription = label) },
                    label = { Text(label) },
                    selected = isSelected(currentRoute),
                    onClick = {
                        navBarItemClick(
                            route = route, navController = navController
                        )
                    }
                )
        }
        NavigationBar {
            NavBarItemCreator(
                route = HOME_SCREEN,
                currentRoute = currentRoute,
                icon = Icons.Default.Home,
                label = HOME,
                navController = navController,
                isSelected = { it == HOME_SCREEN }
            )
            NavBarItemCreator(
                route = LANGUAGE_SCREEN,
                currentRoute = currentRoute,
                icon = Icons.Default.Search,
                label = SEARCH,
                navController = navController,
                isSelected = { it == LANGUAGE_SCREEN || it?.startsWith(SELECTED_LANGUAGE_SCREEN) == true }
            )
        }
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

@Composable
fun NavBarItem(
    route: String,
    currentRoute: String?,
    icon: ImageVector,
    label: String,
    navController: NavController,
    isSelected: (String?) -> Boolean
) {
   NavigationBar {
       NavigationBarItem(
           icon = { Icon(icon, contentDescription = label) },
           label = { Text(label) },
           selected = isSelected(currentRoute),
           onClick = {
               navBarItemClick(
                   route = route, navController = navController
               )
           }
       )
   }
}

const val HOME = "Home"
const val SEARCH = "Search"