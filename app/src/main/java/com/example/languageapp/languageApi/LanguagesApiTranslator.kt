package com.example.languageapp.languageApi

import retrofit2.http.GET
import retrofit2.http.Header

interface LanguagesApiTranslator {
    @GET("v3/languages")
    suspend fun getLanguages(
        @Header("Authorization") token: String
    ): List<LanguagesResponse>
}