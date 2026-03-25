package com.example.languageapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.languageapp.appnavigation.AppNavigation
import com.example.languageapp.common.SharedPreferencesHelper
import com.example.languageapp.ui.theme.MyApplicationTheme
import com.example.languageapp.languageApi.RetrofitInstance
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initKoin()
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                AppNavigation()
            }
        }
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MainActivity)
            modules(appModule)
        }
    }
}

