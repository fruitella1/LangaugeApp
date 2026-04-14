package com.example.languageapp

import com.example.languageapp.common.SharedPreferencesHelper
import com.example.languageapp.languageApi.LanguagesRetrofitInstance
import com.example.languageapp.translationApi.TranslationRetrofitInstance
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { SharedPreferencesHelper(androidContext()) }
    single { LanguagesRetrofitInstance }
    single { TranslationRetrofitInstance }
}