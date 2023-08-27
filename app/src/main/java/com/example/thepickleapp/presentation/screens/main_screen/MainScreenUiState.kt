package com.example.thepickleapp.presentation.screens.main_screen

import com.example.thepickleapp.data.dao.PickleResultDaoBase
import com.example.thepickleapp.presentation.utils.ErrorData

sealed class MainScreenUiState {
    data class Success(
        val data: List<PickleResultDaoBase>,
        val isPaging : Boolean,
        val isLastPage : Boolean
    ) : MainScreenUiState()

    data class Error(val errorData: ErrorData?) : MainScreenUiState()
    object Loading : MainScreenUiState()
}
