package com.example.thepickleapp.presentation.common_views.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.thepickleapp.R
import com.example.thepickleapp.presentation.common_views.PickleChip
import com.example.thepickleapp.presentation.main_screen.search.utils.EpisodeFilterType
import com.example.thepickleapp.presentation.main_screen.search.state.ExtraFiltersData
import com.example.thepickleapp.presentation.main_screen.search.state.ExtraFiltersState
import com.example.thepickleapp.presentation.main_screen.search.state.QueryState
import com.example.thepickleapp.presentation.main_screen.search.state.changeExtraFilterData
import com.example.thepickleapp.presentation.main_screen.search.state.changeQueryType
import com.example.thepickleapp.presentation.main_screen.search.state.expandExtraFilters
import com.example.thepickleapp.presentation.main_screen.search.state.hideExtraFilters
import com.example.thepickleapp.presentation.main_screen.search.utils.SearchType

@Composable
fun PickleSearchFilters(
    modifier: Modifier,
    queryState: QueryState,
    queryStateChange: (QueryState) -> Unit
) {
    Box(modifier = modifier) {
        Column() {
            SearchTypeFilter(queryState.selectedQueryType) { newType ->
                queryStateChange(queryState.changeQueryType(newType))

            }
            ExtraFiltersButton(queryState.extraFiltersState) {
                queryStateChange(queryState.expandExtraFilters())
            }
            ExtraFiltersContainer(
                queryState.extraFiltersState, queryState.selectedQueryType,
                hideExtraFilters = { queryStateChange(queryState.hideExtraFilters()) },
            ) { newFilterData ->
                queryStateChange(queryState.changeExtraFilterData(newFilterData))
            }
        }
    }
}

@Composable
fun ExtraFiltersContainer(
    extraFiltersState: ExtraFiltersState,
    searchTypeFilter: String,
    hideExtraFilters: () -> Unit,
    setNewFilterData: (ExtraFiltersData) -> Unit
) {
    AnimatedVisibility(visible = extraFiltersState.isExpanded) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                this@Column.AnimatedVisibility(
                    visible = searchTypeFilter == SearchType.characters
                ) {
                    CharactersExtraFiltersContainer(extraFiltersState.extraFiltersData) { newFilterData ->
                        setNewFilterData(newFilterData)
                    }
                }
                this@Column.AnimatedVisibility(
                    visible = searchTypeFilter == SearchType.locations
                ) {
                    LocationExtraFiltersContainer(extraFiltersState.extraFiltersData) { newFilterData ->
                        setNewFilterData(newFilterData)
                    }
                }
                this@Column.AnimatedVisibility(
                    visible = searchTypeFilter == SearchType.episodes
                ) {
                    EpisodesExtraFiltersContainer(extraFiltersState.extraFiltersData) { newFilterData ->
                        setNewFilterData(newFilterData)
                    }
                }
            }
            Icon(painter = painterResource(id = R.drawable.baseline_keyboard_arrow_up_24),
                contentDescription = "Hide Extra Filters",
                modifier = Modifier
                    .padding(
                        top = 16.dp
                    )
                    .clickable {
                        hideExtraFilters()
                    })
        }
    }
}

@Composable
fun ExtraFiltersButton(extraFiltersState: ExtraFiltersState, expandExtraFilters: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 1
                )
            )
    ) {
        Column() {
            Row() {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .clip(RoundedCornerShape(16))
                        .background(
                            if (extraFiltersState.filtersAreApplied) {
                                MaterialTheme.colorScheme.secondaryContainer
                            } else {
                                MaterialTheme.colorScheme.background
                            }
                        )
                ) {
                    Icon(
                        modifier = Modifier.padding(2.dp),
                        painter = painterResource(id = R.drawable.baseline_tune_24),
                        contentDescription = "Extra Filter Icon"
                    )
                }
                AnimatedVisibility(visible = !extraFiltersState.isExpanded) {
                    Icon(painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                        contentDescription = "Expand extra filters",
                        modifier = Modifier.clickable {
                            expandExtraFilters()
                        })
                }
            }
        }
    }
}

@Composable
fun SearchTypeFilter(searchTypeFilter: String, typeSelected: (String) -> Unit) {
    Column() {
        Text(text = "Type")
        PickleSearchTypeChipGroup(searchTypeFilter) { newType ->
            typeSelected(newType)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PickleSearchTypeChipGroup(initialType: String, typeSelected: (String) -> Unit) {
    var selectedType by remember {
        mutableStateOf(initialType)
    }
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        PickleChip(
            iconResource = R.drawable.baseline_face_24,
            text = SearchType.characters,
            isSelected = selectedType == SearchType.characters,
            contentDescription = SearchType.characters
        ) {
            selectedType = SearchType.characters
            typeSelected(selectedType)
        }
        PickleChip(
            iconResource = R.drawable.baseline_location_on_24,
            text = SearchType.locations,
            isSelected = selectedType == SearchType.locations,
            contentDescription = SearchType.locations
        ) {
            selectedType = SearchType.locations
            typeSelected(selectedType)
        }
        PickleChip(
            iconResource = R.drawable.baseline_video_library_24,
            text = SearchType.episodes,
            isSelected = selectedType == SearchType.episodes,
            contentDescription = SearchType.episodes
        ) {
            selectedType = SearchType.episodes
            typeSelected(selectedType)
        }
    }
}
