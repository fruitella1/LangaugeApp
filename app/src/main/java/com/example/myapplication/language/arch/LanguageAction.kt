package com.example.myapplication.language.arch

sealed interface LanguageAction {
    data class Language (val textChanged: String): LanguageAction
}

