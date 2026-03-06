package com.example.languageapp.language.arch

sealed interface LanguageAction {
    data class Language(val textChanged: String) : LanguageAction
}

