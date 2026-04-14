package com.example.languageapp.translationApi

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface TranslationApiTranslator {
    @POST("/language/translate/v2")
    suspend fun getTranslation (
        @Header("Authorization") token: String,
        @Body request: TranslationRequest
    ): TranslationResponse
}