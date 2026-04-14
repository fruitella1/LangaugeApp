package com.example.languageapp.common

import android.content.Context
import androidx.core.content.edit

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
}
private const val LANGUAGES_APP_PREFS = "My_languages"
private const val LANGUAGE_SAVED = "language_saved"
private const val SELECTED_LANGUAGE_PREFS ="My_selected_language"
private const val SELECTED_LANGUAGE = "selected_language"


