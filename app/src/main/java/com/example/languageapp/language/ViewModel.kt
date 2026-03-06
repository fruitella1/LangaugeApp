package com.example.languageapp.language

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
        "English",
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

    fun onAction(action: LanguageAction) {
        when (action) {
            is LanguageAction.Language -> {
                val text = action.textChanged
                val filtered = if (text.isEmpty()) {
                    state.value.languages
                } else {
                    state.value.languages.filter {
                        it.language.contains(text, ignoreCase = true)
                    }
                }
                _state.value = LanguageState(text = text, languages = filtered)
            }
        }
    }
}