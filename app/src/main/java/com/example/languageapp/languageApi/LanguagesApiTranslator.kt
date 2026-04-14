package com.example.languageapp.languageApi

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiRequests {
    @GET("v3/languages")
    suspend fun getLanguages(
        @Header("Authorization") token: String
    ): List<LanguagesResponse>

    @POST("https://api.langbly.com/language/translate/v2")
    suspend fun getTranslation (
        @Header("Authorization") token: String,
        @Body request: TranslationRequest
    ): TranslationResponse
}