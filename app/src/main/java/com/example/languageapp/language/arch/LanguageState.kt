package com.example.languageapp.language.arch

import androidx.compose.runtime.Immutable
import java.util.UUID

@Immutable
data class LanguageState(
    val text: String = "",
    val filteredLanguages: List<LanguageItem> = emptyList(),
    val allLanguages: List<LanguageItem> = emptyList()
)

@Immutable
data class LanguageItem(
    val id: String = UUID.randomUUID().toString(),
    val language: String
)