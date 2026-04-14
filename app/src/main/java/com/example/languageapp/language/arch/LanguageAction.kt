package com.example.languageapp.language.arch

sealed interface LanguageAction {
    data class LanguageValueChanged(val textChanged: String) : LanguageAction
    data class LanguageSelected(val item: LanguageItem) : LanguageAction
    data class ChangedTextToTranslate(val textToTranslate: String ) : LanguageAction
}

