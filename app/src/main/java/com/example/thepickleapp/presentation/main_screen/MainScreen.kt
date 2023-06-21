package com.example.thepickleapp.presentation.main_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thepickleapp.presentation.common_views.BaseErrorScreen
import com.example.thepickleapp.presentation.common_views.EmptyLoadingScreen

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        val screenState by viewModel.uiState
        GetMainViewByState(screenState)
    }
}

@Composable
fun GetMainViewByState(screenState: MainScreenUiState) {
    when (screenState) {
        is MainScreenUiState.Loading -> {
            EmptyLoadingScreen()
        }

        is MainScreenUiState.Error -> {
            BaseErrorScreen(errorData = screenState.errorData)
        }

        is MainScreenUiState.Success -> {
            MainSuccessScreen(characterResultContainer = screenState.data)
        }
    }
}
