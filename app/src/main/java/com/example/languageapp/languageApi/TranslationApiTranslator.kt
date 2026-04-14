package com.example.languageapp.languageApi

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

private const val TRANSLATION_ROUTE = "language/translate/v2"
interface TranslationApiTranslator {
    @POST(TRANSLATION_ROUTE)
    suspend fun getTranslation (
        @Header("Authorization") token: String,
        @Body request: TranslationRequest
    ): TranslationResponse
}