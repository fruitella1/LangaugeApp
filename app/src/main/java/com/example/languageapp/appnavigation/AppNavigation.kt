package com.example.languageapp.appnavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.languageapp.common.SharedPreferencesHelper
import com.example.languageapp.language.LanguageViewModel
import com.example.languageapp.home.HomeScreen
import com.example.languageapp.language.ui.LanguageScreen
import com.example.languageapp.languageApi.RetrofitInstance

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBarNavigation(navController) },
        modifier = Modifier.background(Color.LightGray)
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = HOME_SCREEN,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(HOME_SCREEN) {
                HomeScreen()
            }

            composable(LANGUAGE_SCREEN) {
                LanguageScreen()
            }
        }
    }
}

const val LANGUAGE_SCREEN = "LanguageScreen"
const val HOME_SCREEN = "HomeScreen"