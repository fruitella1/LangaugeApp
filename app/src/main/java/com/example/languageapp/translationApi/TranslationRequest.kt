package com.example.languageapp.translationApi

data class TranslationRequest (
    val q: String,
    val target: String
)