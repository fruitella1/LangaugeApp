package com.example.languageapp.language.di

import com.example.languageapp.common.SharedPreferencesHelper
import com.example.languageapp.language.KeyEncryption
import com.example.languageapp.language.LanguageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val languageModule = module {
    single { KeyEncryption(get()) }
    viewModel {
        LanguageViewModel(
            preferencesHelper = get(),
            retrofitInstance = get(),
            cryptoManager = get(),
            keyEncryption = get(),
        )
    }
}