package com.example.thepickleapp.presentation.main_screen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thepickleapp.data.dao.character.CharacterResponseContainer
import com.example.thepickleapp.data.dao.episode.EpisodeResponseContainer
import com.example.thepickleapp.data.dao.location.LocationResponseContainer
import com.example.thepickleapp.presentation.common_views.BaseErrorScreen
import com.example.thepickleapp.presentation.common_views.EmptyLoadingScreen
import com.example.thepickleapp.presentation.common_views.search.PickleSearchBar
import com.example.thepickleapp.presentation.common_views.search.PickleSearchFilters
import com.example.thepickleapp.presentation.common_views.search.PickleSearchSummary
import com.example.thepickleapp.presentation.main_screen.search.state.QueryState
import com.example.thepickleapp.presentation.main_screen.search.state.changeQueryInput

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val resultState by viewModel.uiState
    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                Column() {
                    GetSearchToolsView() { newQueryData ->
                        viewModel.peformNewQuery(newQueryData)
                    }
                }
            }
        ) { padding ->
            GetResultViewByState(screenState = resultState, padding)
        }
    }
}

@Composable
fun GetSearchToolsView(
    newQuery: (QueryState) -> Unit
) {
    var queryState by remember {
        mutableStateOf(QueryState.getInitialQueryState())
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 1
                )
            )
    ) {
        PickleSearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) { newSearchPrompt ->
            queryState = queryState.changeQueryInput(newSearchPrompt)
            newQuery(queryState)
        }
        PickleSearchFilters(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            queryState = queryState,
        ) { newQuery ->
            queryState = newQuery
        }
        PickleSearchSummary(queryState)
    }
}

@Composable
fun GetResultViewByState(screenState: MainScreenUiState, padding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        when (screenState) {
            is MainScreenUiState.Loading -> {
                EmptyLoadingScreen()
            }

            is MainScreenUiState.Error -> {
                BaseErrorScreen(errorData = screenState.errorData)
            }

            is MainScreenUiState.Success -> {
                //ToDo should have straight forwards deserialization, this will allow to have multiple
                // types of cells in this same composable
                when (screenState.data) {
                    is CharacterResponseContainer -> {
                        MainListView(listState = screenState.data.results)
                    }

                    is EpisodeResponseContainer -> {
                        MainListView(listState = screenState.data.results)
                    }

                    is LocationResponseContainer -> {
                        MainListView(listState = screenState.data.results)
                    }
                }
            }
        }
    }
}