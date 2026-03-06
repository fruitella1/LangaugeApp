package com.example.languageapp.language.arch

sealed interface LanguageAction {
    data class LanguageValueChanged(val textChanged: String) : LanguageAction
}

