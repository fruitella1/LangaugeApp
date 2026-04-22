package com.example.languageapp.language.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.languageapp.appnavigation.HOME_SCREEN
import com.example.languageapp.appnavigation.SELECTED_LANGUAGE_SCREEN
import com.example.languageapp.language.LanguageViewModel
import com.example.languageapp.language.arch.LanguageAction
import com.example.languageapp.language.di.languageModule
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.module.rememberKoinModules
import org.koin.core.annotation.KoinExperimentalAPI


@OptIn(KoinExperimentalAPI::class, ExperimentalFoundationApi::class)
@Composable
fun LanguageScreen(navController: NavController) {
    rememberKoinModules(unloadModules = true) {
        listOf(languageModule)
    }

    val viewModel = koinViewModel<LanguageViewModel>()
    val state by viewModel.state.collectAsState()

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .statusBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(space = 2.dp)
    ) {
        stickyHeader {
            TextField(
                value = state.textSearch,
                onValueChange = {
                    viewModel.onAction(
                        LanguageAction.LanguageValueChanged(textChanged = it)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
            )
        }

        item {
            Spacer(Modifier.height(8.dp))
        }

        items(
            items = state.filteredLanguages,
            key = { it.id }
        ) { item ->
            Text(
                text = item.language,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        viewModel.onAction(LanguageAction.LanguageSelected(item))

                        navController.navigate("$SELECTED_LANGUAGE_SCREEN/${item.code}") {
                            popUpTo(HOME_SCREEN)
                        }
                    },
            )
        }

        item {
            Spacer(Modifier.height(8.dp))
        }
    }
}