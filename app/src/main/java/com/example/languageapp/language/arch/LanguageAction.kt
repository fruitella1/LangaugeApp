package com.example.languageapp.language.arch

import android.content.Context

sealed interface LanguageAction {
    data class LanguageValueChanged(val textChanged: String) : LanguageAction
}

