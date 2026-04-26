package com.example.languageapp.common

import android.content.Context
import androidx.core.content.edit
import com.example.languageapp.cryptomanager.EncryptedData
import android.util.Base64

class SharedPreferencesHelper(private val context: Context) {
    fun getLanguages(): Set<String> {
        val sharedPref = context.getSharedPreferences("My_languages", Context.MODE_PRIVATE)
        val saved = sharedPref.getStringSet("language_saved", null)

        return if (saved.isNullOrEmpty()) {
            val defaultLanguages = setOf(
                "Russian", "English", "American", "Angolan", "Prussian",
                "Abkhazian", "Afar", "Afrikaans", "Akan", "Albanian",
                "Amharic", "Arabic", "Aragonese", "Armenian", "Assamese",
                "Avaric", "Avestan", "Aymara", "Azerbaijani", "Bambara",
                "Bashkir", "Basque", "Belarusian", "Bengali", "Bihari",
                "Bislama", "Bosnian", "Breton", "Bulgarian", "Burmese",
                "Catalan", "Chamorro", "Chechen", "Chichewa", "Chinese",
                "Chuvash", "Cornish", "Corsican", "Cree", "Croatian",
                "Czech", "Danish", "Divehi", "Dutch", "Dzongkha",
                "Esperanto", "Estonian", "Ewe", "Faroese", "Fijian",
                "Finnish", "French", "Fula", "Galician", "Georgian",
                "German", "Gikuyu", "Greek", "Guarani", "Gujarati",
                "Haitian", "Hausa", "Hebrew", "Herero", "Hindi",
                "Hiri Motu", "Hungarian", "Icelandic", "Ido", "Igbo",
                "Indonesian", "Interlingua", "Interlingue", "Inuktitut", "Inupiaq",
                "Irish", "Italian", "Japanese", "Javanese", "Kannada",
                "Kanuri", "Kashmiri", "Kazakh", "Khmer", "Kikuyu",
                "Kinyarwanda", "Kirghiz", "Kirundi", "Komi", "Kongo",
                "Korean", "Kwanyama", "Kurdish", "Lao", "Latin",
                "Latvian", "Limburgish", "Lingala", "Lithuanian", "Luba-Katanga",
                "Luxembourgish", "Macedonian", "Malagasy", "Malay", "Malayalam",
                "Maltese", "Manx", "Maori", "Marathi", "Marshallese",
                "Mongolian", "Nauru", "Navajo", "Ndonga"
            )
            sharedPref.edit { putStringSet("language_saved", defaultLanguages.toSet()) }
            defaultLanguages
        } else {
            saved
        }
    }

    fun setLanguages(languages: Set<String>) {
        val sharedPref = context.getSharedPreferences(LANGUAGES_APP_PREFS, Context.MODE_PRIVATE)
        sharedPref.edit { putStringSet(LANGUAGE_SAVED, languages.toSet()) }
    }

    fun saveLanguages(languages: List<String>) {
        val sharedPref = context.getSharedPreferences(LANGUAGES_APP_PREFS, Context.MODE_PRIVATE)
        sharedPref.edit { putStringSet(LANGUAGE_SAVED,languages.toSet()) }
    }

    fun saveSelectedLanguage(code: String) {
        val sharedPref = context.getSharedPreferences(SELECTED_LANGUAGE_PREFS, Context.MODE_PRIVATE)
        sharedPref.edit { putString(SELECTED_LANGUAGE, code) }
    }

    fun getSelectedLanguage(): String {
        val sharedPref = context.getSharedPreferences(SELECTED_LANGUAGE_PREFS, Context.MODE_PRIVATE)
        return sharedPref.getString(SELECTED_LANGUAGE, "en") ?: "en"
    }

    fun saveEncryptedValue(name: String, encryptedData: EncryptedData) {
        val sharedPref = context.getSharedPreferences(ENCRYPTED_PREFS, Context.MODE_PRIVATE)
        sharedPref.edit {
            putString("${name}_cipher", Base64.encodeToString(encryptedData.cipherText, Base64.NO_WRAP))
            putString("${name}_iv", Base64.encodeToString(encryptedData.iv, Base64.NO_WRAP))
        }
    }

    fun getEncryptedValue(name: String): EncryptedData {
        val sharedPref = context.getSharedPreferences(ENCRYPTED_PREFS, Context.MODE_PRIVATE)

        val cipherTextBase64 = sharedPref.getString("${name}_cipher", "")
        val ivBase64 = sharedPref.getString("${name}_iv", "")

        return EncryptedData(
            cipherText = Base64.decode(cipherTextBase64, Base64.NO_WRAP),
            iv = Base64.decode(ivBase64, Base64.NO_WRAP)
        )
    }

}
private const val LANGUAGES_APP_PREFS = "my_languages"
private const val LANGUAGE_SAVED = "language_saved"
private const val SELECTED_LANGUAGE_PREFS ="my_selected_language"
private const val SELECTED_LANGUAGE = "selected_language"
private const val ENCRYPTED_PREFS = "encrypted_prefs"


