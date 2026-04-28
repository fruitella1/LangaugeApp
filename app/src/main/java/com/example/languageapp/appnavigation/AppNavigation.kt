package com.example.languageapp.appnavigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.languageapp.home.HomeScreen
import com.example.languageapp.language.ui.LanguageScreen
import com.example.languageapp.selectedlanguagescreen.SelectedLanguageScreen

@Composable
fun AppNavigation() {
    val appNavController = rememberNavController()
    val bottomBarHeight = 110.dp
    Box {
        NavHost(
            navController = appNavController,
            startDestination = HOME_SCREEN,
            modifier = Modifier.padding(bottom = bottomBarHeight)
        ) {
            composable(route = HOME_SCREEN) {
                HomeScreen()
            }

            composable(route = LANGUAGE_SCREEN) {
                LanguageScreen(appNavController)
            }

            composable(
                route = SELECTED_LANGUAGE_ROUTE,
                arguments = listOf(navArgument(SELECTED_LANGUAGE_NAV_ARGUMENT) { type = NavType.StringType })
            ) { backStackEntry ->
                val selectedLanguage =
                    backStackEntry.arguments?.getString(SELECTED_LANGUAGE_NAV_ARGUMENT).orEmpty()
                SelectedLanguageScreen(selectedLanguage, appNavController)
            }
        }

        BottomBarNavigation(
            navController = appNavController,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .height(bottomBarHeight)
        )
    }
}

const val LANGUAGE_SCREEN = "LanguageScreen"
const val HOME_SCREEN = "HomeScreen"
const val SELECTED_LANGUAGE_SCREEN = "SelectedLanguageScreen"
const val SELECTED_LANGUAGE_NAV_ARGUMENT = "SelectedLanguageNavArgument"
const val SELECTED_LANGUAGE_ROUTE = "$SELECTED_LANGUAGE_SCREEN/${SELECTED_LANGUAGE_NAV_ARGUMENT}"