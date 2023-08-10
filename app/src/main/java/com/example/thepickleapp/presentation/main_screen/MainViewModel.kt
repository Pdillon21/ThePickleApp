package com.example.thepickleapp.presentation.main_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thepickleapp.di.IoDispatcher
import com.example.thepickleapp.domain.use_cases.GetCharactersUseCase
import com.example.thepickleapp.domain.use_cases.GetEpisodesUseCase
import com.example.thepickleapp.domain.use_cases.GetLocationsUseCase
import com.example.thepickleapp.presentation.main_screen.search.state.QueryState
import com.example.thepickleapp.presentation.main_screen.search.utils.SearchType
import com.example.thepickleapp.presentation.utils.ErrorUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.IllegalStateException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getEpisodesUseCase: GetEpisodesUseCase,
    private val getLocationsUseCase: GetLocationsUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = mutableStateOf(MainScreenUiState.Loading as MainScreenUiState)
    val uiState: State<MainScreenUiState> = _uiState

    private val _query = mutableStateOf(QueryState.getInitialQueryState())
    val query: State<QueryState> = _query

    private val coroutineExceptionHandler = CoroutineExceptionHandler { a, b ->
        asignBasicError()
    }

    init {
        query()
    }


    private fun asignBasicError() {
        viewModelScope.launch(Dispatchers.Main) {
            _uiState.value = MainScreenUiState.Error(errorData = ErrorUtils.getDefaultErrorData())
        }
    }

    fun query() {
        viewModelScope.launch(dispatcher + coroutineExceptionHandler) {
            val currentQueryState = getCurrentQuerySate()
            when (currentQueryState.selectedQueryType) {
                SearchType.characters -> {
                    getCharactersUseCase.invoke(currentQueryState).collectLatest { flowUiState ->
                        withContext(Dispatchers.Main) {
                            _uiState.value = flowUiState
                        }
                    }
                }

                SearchType.episodes -> {
                    getEpisodesUseCase.invoke(currentQueryState).collectLatest { flowUiState ->
                        withContext(Dispatchers.Main) {
                            _uiState.value = flowUiState
                        }
                    }
                }

                SearchType.locations -> {
                    getLocationsUseCase.invoke(currentQueryState).collectLatest { flowUiState ->
                        withContext(Dispatchers.Main) {
                            _uiState.value = flowUiState
                        }
                    }
                }
            }
        }
    }

    private fun getCurrentQuerySate(): QueryState {
        return try {
            query.value
        } catch (e: IllegalStateException) {
            QueryState.getInitialQueryState()
        }
    }

    fun setQueryData(newQueryData: QueryState) {
        if (
            _query.value.selectedQueryType != newQueryData.selectedQueryType
        ) {
            _query.value = newQueryData
            query()
        } else {
            _query.value = newQueryData
        }
    }
}