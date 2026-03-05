package com.example.languageapp.language.arch

import androidx.compose.runtime.mutableStateListOf

data class LanguageState(
    val text: String = "",
    val languages: List<String> = emptyList()
)