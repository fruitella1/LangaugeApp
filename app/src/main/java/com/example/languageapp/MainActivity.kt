package com.example.languageapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.languageapp.appnavigation.AppNavigation
import com.example.languageapp.common.SharedPreferencesHelper
import com.example.languageapp.ui.theme.MyApplicationTheme
import com.example.languageapp.languageApi.RetrofitInstance


class MainActivity : ComponentActivity() {
    private val prefsHelper = SharedPreferencesHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                AppNavigation(
                    sharedPreferencesHelper = prefsHelper,
                    retrofitInstance = RetrofitInstance
                )
            }
        }
    }
}

