package com.example.languageapp

import com.example.languageapp.common.SharedPreferencesHelper
import com.example.languageapp.languageApi.RetrofitInstance
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { SharedPreferencesHelper(androidContext()) }
    single { RetrofitInstance }
}