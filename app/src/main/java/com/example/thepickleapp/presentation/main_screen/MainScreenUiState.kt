package com.example.thepickleapp.presentation.main_screen

import com.example.thepickleapp.data.dao.ResponseContainerBase
import com.example.thepickleapp.presentation.utils.ErrorData

sealed class MainScreenUiState {
    data class Success(val data: ResponseContainerBase) : MainScreenUiState()
    data class Error(val errorData: ErrorData?) : MainScreenUiState()
    object Loading : MainScreenUiState()
}
