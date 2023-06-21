package com.example.thepickleapp.presentation.main_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thepickleapp.di.IoDispatcher
import com.example.thepickleapp.domain.use_cases.GetCharactersUseCase
import com.example.thepickleapp.presentation.utils.ErrorUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = mutableStateOf(MainScreenUiState.Loading as MainScreenUiState)
    val uiState: State<MainScreenUiState> = _uiState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { a, b ->
        asignBasicError()
    }

    init {
        getAllCharacters()
    }


    private fun asignBasicError() {
        viewModelScope.launch(Dispatchers.Main) {
            _uiState.value = MainScreenUiState.Error(errorData = ErrorUtils.getDefaultErrorData())
        }
    }

    private fun getAllCharacters() {
        viewModelScope.launch(dispatcher + coroutineExceptionHandler) {
            getCharactersUseCase.invoke().collectLatest { flowUiState ->
                withContext(Dispatchers.Main) {
                    _uiState.value = flowUiState
                }
            }
        }
    }
}