package com.example.languageapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.languageapp.AppNavigation.AppNavigation
import com.example.languageapp.language.LanguageViewModel
import com.example.languageapp.language.ui.LanguageScreen
import com.example.languageapp.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {

    public val viewModel by viewModels<LanguageViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                LanguageScreen(viewModel)
                AppNavigation()
            }
        }
    }
}

