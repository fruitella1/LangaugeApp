package com.example.api

data class TranslationResponse (
    val data: TranslationData
)

data class TranslationData (
    val translations: List<TranslationItem>
)

data class TranslationItem (
    val translatedText: String,
    val detectedSourceLanguage: String
)