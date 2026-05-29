package com.example.languageapp.language.di


import com.example.crypto.cryptomanager.KeyEncryption
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