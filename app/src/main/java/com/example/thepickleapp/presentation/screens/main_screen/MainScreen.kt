package com.example.thepickleapp.presentation.screens.main_screen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thepickleapp.R
import com.example.thepickleapp.presentation.common_views.EmptyLoadingScreen
import com.example.thepickleapp.presentation.common_views.search.PickleSearchFilters
import com.example.thepickleapp.presentation.common_views.search.PickleSearchSummary
import com.example.thepickleapp.presentation.screens.main_screen.search.state.QueryState
import com.example.thepickleapp.presentation.screens.main_screen.search.state.changeQueryInput
import com.example.thepickleapp.presentation.common_views.search.PickleElevatedSearchBar
import com.example.thepickleapp.presentation.screens.error_screen.BaseErrorScreen
import com.example.thepickleapp.presentation.ui.theme.pickleAppColors

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val resultState by viewModel.uiState
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                SearchContainer() { newQueryData ->
                    viewModel.setQueryData(newQueryData)
                    viewModel.query()
                }
            }
        ) { padding ->
            GetResultViewByState(screenState = resultState, padding) {
                viewModel.query()
            }
        }
    }
}

@Composable
fun SearchContainer(
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
        PickleElevatedSearchBar(
            modifier = Modifier.padding(horizontal = 8.dp),
            currentQuery = queryState.query,
            hint = stringResource(R.string.name_to_search_hint),
            buttonVisible = true,
            onQuerryChange = {
                queryState = queryState.changeQueryInput(it)
            },
            onButtonClicked = {
                newQuery(queryState)
            }
        )
        PickleSearchFilters(
            modifier = Modifier
                .fillMaxWidth(),
            queryState = queryState,
        ) { newQuery ->
            if (queryState.selectedQueryType != newQuery.selectedQueryType) {
                queryState = newQuery
                newQuery(queryState)
            } else {
                queryState = newQuery
            }
        }
        PickleSearchSummary(queryState)
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(
                    pickleAppColors()
                        .onSurface
                )
        )
    }
}

@Composable
fun GetResultViewByState(
    screenState: MainScreenUiState,
    padding: PaddingValues,
    listEndReached: () -> Unit
) {
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
                BaseErrorScreen(
                    errorTitle = screenState.errorData?.errorTitle,
                    errorMessage = screenState.errorData?.errorMessage
                )
            }

            is MainScreenUiState.Success -> {
                MainListView(
                    listState = screenState.data,
                    isPaging = screenState.isPaging,
                    noMoreResults = screenState.isLastPage
                ) {
                    listEndReached()
                }
            }
        }
    }
}