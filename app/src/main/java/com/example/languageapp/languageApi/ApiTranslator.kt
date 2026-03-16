package com.example.languageapp.languageApi

import com.google.gson.stream.JsonToken
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiTranslator {
    @GET("languages")
    suspend fun getLanguages(
        @Header("Authorization") token: String
    ): List<LanguagesResponse>
}