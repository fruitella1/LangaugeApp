package com.example.languageapp.language.di

import com.example.languageapp.language.LanguageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val languageModule = module {
    viewModel { LanguageViewModel(
        preferencesHelper = get(), languagesInstance = get()) }
}