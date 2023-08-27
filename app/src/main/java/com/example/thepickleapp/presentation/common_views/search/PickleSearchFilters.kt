package com.example.thepickleapp.presentation.common_views.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.thepickleapp.R
import com.example.thepickleapp.presentation.common_views.general.ElevatedButton
import com.example.thepickleapp.presentation.common_views.general.ElevatedContainer
import com.example.thepickleapp.presentation.common_views.general.PickleChip
import com.example.thepickleapp.presentation.screens.main_screen.search.state.ExtraFiltersData
import com.example.thepickleapp.presentation.screens.main_screen.search.state.ExtraFiltersState
import com.example.thepickleapp.presentation.screens.main_screen.search.state.QueryState
import com.example.thepickleapp.presentation.screens.main_screen.search.state.changeExtraFilterData
import com.example.thepickleapp.presentation.screens.main_screen.search.state.changeQueryType
import com.example.thepickleapp.presentation.screens.main_screen.search.state.expandExtraFilters
import com.example.thepickleapp.presentation.screens.main_screen.search.state.hideExtraFilters
import com.example.thepickleapp.presentation.screens.main_screen.search.utils.SearchType
import com.example.thepickleapp.presentation.ui.theme.pickleAppColors

@Composable
fun PickleSearchFilters(
    modifier: Modifier,
    queryState: QueryState,
    queryStateChange: (QueryState) -> Unit
) {
    Box(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = 4.dp,
                alignment = Alignment.Top
            )
        ) {
            SearchTypeFilter(queryState.selectedQueryType) { newType ->
                queryStateChange(queryState.changeQueryType(newType))

            }
            ElevatedContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(8.dp),
                color = pickleAppColors().extraFiltersSurface,
                sideElevation = 2.dp,
                bottomElevation = 2.dp,
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(
                        space = 4.dp,
                        alignment = Alignment.Top
                    )
                ) {
                    ExtraFiltersButton(
                        extraFiltersState = queryState.extraFiltersState,
                        expandExtraFilters = { queryStateChange(queryState.expandExtraFilters()) },
                        hideExtraFilters = { queryStateChange(queryState.hideExtraFilters()) }
                    )
                    ExtraFiltersContainer(
                        queryState.extraFiltersState, queryState.selectedQueryType,
                        hideExtraFilters = { queryStateChange(queryState.hideExtraFilters()) },
                    ) { newFilterData ->
                        queryStateChange(queryState.changeExtraFilterData(newFilterData))
                    }
                }
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
            Spacer(modifier = Modifier.height(16.dp))
            HideExtraFiltersButton() {
                hideExtraFilters()
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun HideExtraFiltersButton(hideExtraFilters: () -> Unit) {
    ElevatedButton(
        modifier = Modifier.padding(horizontal = 4.dp),
        color = pickleAppColors().buttonAccent,
        bottomElevation = 2.dp,
        sideElevation = 2.dp,
        clickable = true,
        content = {
            Box(modifier = Modifier.padding(horizontal = 36.dp)) {
                Icon(
                    modifier = Modifier
                        .width(24.dp)
                        .aspectRatio(1f),
                    painter = painterResource(id = R.drawable.baseline_keyboard_arrow_up_24),
                    contentDescription = null,
                    tint = pickleAppColors().onSurface
                )
            }
        }
    ) {
        hideExtraFilters()
    }
}

@Composable
fun ExtraFiltersButton(
    extraFiltersState: ExtraFiltersState,
    expandExtraFilters: () -> Unit,
    hideExtraFilters: () -> Unit
) {
    ElevatedButton(
        color = pickleAppColors().buttonAccent,
        bottomElevation = 2.dp,
        sideElevation = 2.dp,
        clickable = true,
        content = {
            Row(
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    space = 4.dp,
                    alignment = Alignment.Start
                )
            ) {
                Text(
                    text = if (extraFiltersState.isExpanded)
                        stringResource(R.string.hide_filters)
                    else
                        stringResource(R.string.show_filters),
                    color = pickleAppColors().onSurface
                )
                Icon(
                    modifier = Modifier
                        .width(24.dp)
                        .aspectRatio(1f),
                    painter = painterResource(
                        id = if (extraFiltersState.isExpanded)
                            R.drawable.baseline_keyboard_arrow_up_24
                        else
                            R.drawable.baseline_keyboard_arrow_down_24
                    ),
                    contentDescription = null,
                    tint = pickleAppColors().onSurface
                )
            }
        }
    ) {
        if (extraFiltersState.isExpanded) {
            hideExtraFilters()
        } else {
            expandExtraFilters()
        }
    }
}

@Composable
fun SearchTypeFilter(searchTypeFilter: String, typeSelected: (String) -> Unit) {
    ElevatedContainer(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        color = pickleAppColors().typeFilterSurface,
        sideElevation = 2.dp,
        bottomElevation = 2.dp,
    ) {
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
            .wrapContentHeight()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        PickleChip(
            iconResource = R.drawable.baseline_face_24,
            text = SearchType.characters,
            colorPrimary = pickleAppColors().charactersAccent,
            isSelected = selectedType == SearchType.characters
        ) {
            selectedType = SearchType.characters
            typeSelected(selectedType)
        }
        PickleChip(
            iconResource = R.drawable.baseline_location_on_24,
            text = SearchType.locations,
            colorPrimary = pickleAppColors().locationsAccent,
            isSelected = selectedType == SearchType.locations
        ) {
            selectedType = SearchType.locations
            typeSelected(selectedType)
        }
        PickleChip(
            iconResource = R.drawable.baseline_video_library_24,
            text = SearchType.episodes,
            colorPrimary = pickleAppColors().episodesAccent,
            isSelected = selectedType == SearchType.episodes
        ) {
            selectedType = SearchType.episodes
            typeSelected(selectedType)
        }
    }
}
