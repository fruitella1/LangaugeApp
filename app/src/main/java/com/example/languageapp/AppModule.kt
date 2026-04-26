package com.example.languageapp

import com.example.languageapp.common.SharedPreferencesHelper
import com.example.languageapp.cryptomanager.CryptoManager
import com.example.languageapp.language.KeyEncryption
import com.example.languageapp.language.LanguageViewModel
import com.example.languageapp.languageApi.RetrofitInstance
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { SharedPreferencesHelper(androidContext()) }
    single { RetrofitInstance }
    single { CryptoManager () }
    single { LanguageViewModel(get(),get(),get(),get()) }
    single { KeyEncryption(get()) }
}