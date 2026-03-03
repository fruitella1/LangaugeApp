package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.myapplication.AppNavigation.AppNavigation
import com.example.myapplication.language.LanguageViewModel
import com.example.myapplication.language.ui.LanguageScreen
import com.example.myapplication.ui.theme.MyApplicationTheme


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

