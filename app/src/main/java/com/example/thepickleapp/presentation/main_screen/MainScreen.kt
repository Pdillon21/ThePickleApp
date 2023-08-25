package com.example.thepickleapp.presentation.main_screen

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thepickleapp.presentation.common_views.BaseErrorScreen
import com.example.thepickleapp.presentation.common_views.EmptyLoadingScreen
import com.example.thepickleapp.presentation.common_views.search.PickleSearchBar
import com.example.thepickleapp.presentation.common_views.search.PickleSearchFilters
import com.example.thepickleapp.presentation.common_views.search.PickleSearchSummary
import com.example.thepickleapp.presentation.main_screen.search.state.QueryState
import com.example.thepickleapp.presentation.main_screen.search.state.changeQueryInput
import com.example.thepickleapp.presentation.common_views.general.ElevatedContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val resultState by viewModel.uiState
    val backgroundColor = Color(0xffFFFCEE)
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = backgroundColor
    ) {
        Scaffold(
            topBar = {
                Column(
                    modifier = Modifier.background(backgroundColor)
                ) {
                    GetSearchToolsView() { newQueryData ->
                        viewModel.setQueryData(newQueryData)
                        viewModel.query()
                    }
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
        ElevatedContainer(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            sideElevation = 2.dp,
            bottomElevation = 2.dp,
            color = Color(0xffFFEEFD)
        ) {
            PickleSearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) { newSearchPrompt ->
                queryState = queryState.changeQueryInput(newSearchPrompt)
                newQuery(queryState)
            }
        }
        PickleSearchFilters(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
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
                .background(Color.Black)
        )
    }
}

@Composable
fun GetResultViewByState(
    screenState: MainScreenUiState,
    padding: PaddingValues,
    listEndReached: () -> Unit
) {
    val backgroundColor = Color(0xffFFFCEE)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(backgroundColor)
    ) {
        when (screenState) {
            is MainScreenUiState.Loading -> {
                EmptyLoadingScreen()
            }

            is MainScreenUiState.Error -> {
                BaseErrorScreen(errorData = screenState.errorData)
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