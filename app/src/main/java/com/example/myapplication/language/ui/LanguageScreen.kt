package com.example.myapplication.language.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.language.LanguageViewModel
import com.example.myapplication.language.arch.LanguageAction


@Composable
fun LanguageScreen(viewModel: LanguageViewModel) {
    val state by viewModel.state.collectAsState()
    Column(
        modifier = Modifier.padding(5.dp, 70.dp)
    ) {
        TextField(
            value = state.text,
            onValueChange = {
                viewModel.onAction(LanguageAction.Language(textChanged = it))
            }
        )
        LanguageList(
            languages = state.languages
        )
    }
}
@Composable
fun LanguageList(languages: MutableList<String>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(
            items = languages,
            key = { it }
        ) { languages ->
            Text(
                text = languages
            )
        }
    }
}


