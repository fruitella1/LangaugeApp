package com.example.languageapp.language.arch

import androidx.compose.runtime.Immutable
import java.util.UUID

@Immutable
data class LanguageState(
    val textSearch: String = "",
    val filteredLanguages: List<LanguageItem> = emptyList(),
    val allLanguages: List<LanguageItem> = emptyList(),
    val textToTranslate: String = "",
    val languageSelectedCode: String = "",
    val translatedText: String = "",
    val errorId: Int? = null,
    val translationDelay: Long = 1000
)

@Immutable
data class LanguageItem(
    val id: String = UUID.randomUUID().toString(),
    val language: String,
    val code: String
)