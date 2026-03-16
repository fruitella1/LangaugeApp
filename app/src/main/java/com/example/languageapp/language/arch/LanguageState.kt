package com.example.languageapp.language.arch

import androidx.compose.runtime.Immutable
import java.util.UUID

@Immutable
data class LanguageState(
    val text: String = "",
    val languages: List<LanguageItem> = emptyList()
)

@Immutable
data class LanguageItem(
    val id: String = UUID.randomUUID().toString(),
    val language: String
)