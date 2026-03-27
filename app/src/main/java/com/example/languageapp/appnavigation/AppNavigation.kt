package com.example.languageapp.appnavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    val bottomBarNavController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBarNavigation(bottomBarNavController) },
        modifier = Modifier.background(Color.LightGray)
    ) { paddingValues ->
        NavHost(
            navController = bottomBarNavController,
            startDestination = HOME_SCREEN,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(HOME_SCREEN) {
                HomeScreen()
            }

            composable(LANGUAGE_SCREEN) {
                LanguageScreen(bottomBarNavController)
            }
            composable(
                "$SELECTED_LANGUAGE_SCREEN/{selectedLanguage}",
                arguments = listOf(navArgument("selectedLanguage") { type = NavType.StringType })
            ) { backStackEntry ->
                val selectedLanguage =
                    backStackEntry.arguments?.getString("selectedLanguage") ?: "Unknown"
                SelectedLanguageScreen(selectedLanguage, bottomBarNavController)
            }
        }
    }
}

const val LANGUAGE_SCREEN = "LanguageScreen"
const val HOME_SCREEN = "HomeScreen"
const val SELECTED_LANGUAGE_SCREEN = "SelectedLanguageScreen"