package com.example.languageapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.languageapp.appnavigation.AppNavigation
import com.example.crypto.cryptomanager.KeyEncryption
import com.example.languageapp.ui.theme.MyApplicationTheme
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initKoin()
        val keyEncryption: KeyEncryption by inject()
        keyEncryption.encryptAndSave()
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

