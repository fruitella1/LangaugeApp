package com.example.languageapp.language

import com.example.languageapp.common.SharedPreferencesHelper
import com.example.languageapp.cryptomanager.CryptoManager

class KeyEncryption(private val preferencesHelper: SharedPreferencesHelper) {
    val cryptoManager = CryptoManager()

    fun encryptAndSave() {
        val languageEncryption =
            cryptoManager.encryptString("Bearer ce1e879146cfcebb5f0b5478eefc0931")
        val translationEncryption = cryptoManager.encryptString("Bearer Lyr7N241PHbvk2sZes8qwC")

        preferencesHelper.saveEncryptedValue(TRANSLATION_KEY, translationEncryption)
        preferencesHelper.saveEncryptedValue(LANGUAGE_KEY, languageEncryption)
    }
}

const val TRANSLATION_KEY = "translation_api"
const val LANGUAGE_KEY = "language_api"