package com.example.languageapp.language

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.languageapp.language.arch.LanguageAction
import com.example.languageapp.language.arch.LanguageItem
import com.example.languageapp.language.arch.LanguageState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LanguageViewModel : ViewModel() {
    private val allLanguages = listOf(
        "Russian",
        "English",
        "American",
        "Angolan",
        "Prussian",
        "Abkhazian",
        "Afar",
        "Afrikaans",
        "Akan",
        "Albanian",
        "Amharic",
        "Arabic",
        "Aragonese",
        "Armenian",
        "Assamese",
        "Avaric",
        "Avestan",
        "Aymara",
        "Azerbaijani",
        "Bambara",
        "Bashkir",
        "Basque",
        "Belarusian",
        "Bengali",
        "Bihari",
        "Bislama",
        "Bosnian",
        "Breton",
        "Bulgarian",
        "Burmese",
        "Catalan",
        "Chamorro",
        "Chechen",
        "Chichewa",
        "Chinese",
        "Chuvash",
        "Cornish",
        "Corsican",
        "Cree",
        "Croatian",
        "Czech",
        "Danish",
        "Divehi",
        "Dutch",
        "Dzongkha",
        "Esperanto",
        "Estonian",
        "Ewe",
        "Faroese",
        "Fijian",
        "Finnish",
        "French",
        "Fula",
        "Galician",
        "Georgian",
        "German",
        "Gikuyu",
        "Greek",
        "Guarani",
        "Gujarati",
        "Haitian",
        "Hausa",
        "Hebrew",
        "Herero",
        "Hindi",
        "Hiri Motu",
        "Hungarian",
        "Icelandic",
        "Ido",
        "Igbo",
        "Indonesian",
        "Interlingua",
        "Interlingue",
        "Inuktitut",
        "Inupiaq",
        "Irish",
        "Italian",
        "Japanese",
        "Javanese",
        "Kannada",
        "Kanuri",
        "Kashmiri",
        "Kazakh",
        "Khmer",
        "Kikuyu",
        "Kinyarwanda",
        "Kirghiz",
        "Kirundi",
        "Komi",
        "Kongo",
        "Korean",
        "Kwanyama",
        "Kurdish",
        "Lao",
        "Latin",
        "Latvian",
        "Limburgish",
        "Lingala",
        "Lithuanian",
        "Luba-Katanga",
        "Luxembourgish",
        "Macedonian",
        "Malagasy",
        "Malay",
        "Malayalam",
        "Maltese",
        "Manx",
        "Maori",
        "Marathi",
        "Marshallese",
        "Mongolian",
        "Nauru",
        "Navajo",
        "Ndonga",
    )

    fun saveLanguages(context: Context) {
        val sharedPref = context.getSharedPreferences("My_languages", Context.MODE_PRIVATE)
        sharedPref.edit().putStringSet("language_saved", allLanguages.toSet()).apply()
        _state.value = LanguageState(languages = allLanguages.map { LanguageItem(language = it) })
    }//упирается в передачу context в onAction

    private val _state: MutableStateFlow<LanguageState> =
        MutableStateFlow(
            LanguageState(
                text = "",
                languages = allLanguages.map {
                    LanguageItem(
                        language = it
                    )
                }
            )
        )
    val state: StateFlow<LanguageState> = _state.asStateFlow()

    fun onAction(action: LanguageAction,context: Context) {
        when (action) {
            is LanguageAction.LanguageValueChanged -> {
                val text = action.textChanged
                val filtered = if (text.isEmpty()) {
                    allLanguages.map { LanguageItem(language = it) }
                } else {
                    allLanguages.filter {
                        it.contains(text, ignoreCase = true)
                    }.map { LanguageItem(language = it) }
                }
                _state.value = LanguageState(text = text, languages = filtered)
            }
        }
    }
}