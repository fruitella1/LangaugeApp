package com.example.languageapp.common

import android.content.Context

class SharedPreferencesHelper(private val context: Context) {
    fun getLanguages(): List<String> {
        val sharedPref = context.getSharedPreferences("My_languages", Context.MODE_PRIVATE)
        val saved = sharedPref.getStringSet("language_saved", null)

        return if (saved.isNullOrEmpty()) {
            val defultLanguages = listOf(
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
            sharedPref.edit().putStringSet("language_saved", defultLanguages.toSet())
            defultLanguages
        } else {
            saved.toList()
        }
    }
    fun setLanguages(languages: List<String>) {
        val sharedPref = context.getSharedPreferences("My_languages", Context.MODE_PRIVATE)
        sharedPref.edit().putStringSet("language_saved", languages.toSet()).apply()
    }
}



