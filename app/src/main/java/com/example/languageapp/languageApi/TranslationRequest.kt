package com.example.languageapp.languageApi

data class TranslationRequest (
    val q: String,
    val target: String
)