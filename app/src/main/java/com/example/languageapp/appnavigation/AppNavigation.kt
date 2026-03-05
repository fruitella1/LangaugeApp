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
import com.example.languageapp.language.LanguageViewModel
import com.example.languageapp.language.ui.HomeScreen
import com.example.languageapp.language.ui.LanguageScreen

const val HomeSc = "HomeScreen"
const val LanguageSc = "LanguageScreen"
@Composable
fun AppNavigation(viewModel: LanguageViewModel) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBarNavigation(navController) },
        modifier = Modifier.background(Color.LightGray)
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "HomeScreen",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(HomeSc) {
                HomeScreen()
            }
            composable(LanguageSc) {
                LanguageScreen(viewModel )
            }
        }
    }
}