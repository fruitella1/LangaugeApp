package com.example.languageapp

import com.example.api.RetrofitInstance
import com.example.crypto.cryptomanager.CryptoManager
import com.example.crypto.cryptomanager.KeyEncryption
import com.example.crypto.preferenes.SharedPreferencesHelper
import com.example.languageapp.language.LanguageViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { SharedPreferencesHelper(androidContext()) }
    single { RetrofitInstance }
    single { CryptoManager() }
    single { LanguageViewModel(get(),get(),get(),get()) }
    single { KeyEncryption(get()) }
}