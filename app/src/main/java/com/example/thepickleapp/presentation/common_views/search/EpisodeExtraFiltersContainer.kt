package com.example.thepickleapp.presentation.common_views.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thepickleapp.R
import com.example.thepickleapp.presentation.common_views.general.PickleChip
import com.example.thepickleapp.presentation.screens.main_screen.search.state.ExtraFiltersData
import com.example.thepickleapp.presentation.screens.main_screen.search.state.changeQueryType
import com.example.thepickleapp.presentation.screens.main_screen.search.utils.EpisodeFilterType

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EpisodesExtraFiltersContainer(
    extraFiltersData: ExtraFiltersData,
    changeExtraFiltersData: (ExtraFiltersData) -> Unit
) {
    if (extraFiltersData is ExtraFiltersData.EpisodesExtraFilters){
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            PickleChip(
                iconResource = R.drawable.baseline_short_text_24,
                text = EpisodeFilterType.episodeFilterByName,
                isSelected = extraFiltersData.episodeQueryType == EpisodeFilterType.episodeFilterByName) {
                changeExtraFiltersData(extraFiltersData.changeQueryType(EpisodeFilterType.episodeFilterByName))
            }
            PickleChip(
                iconResource = R.drawable.baseline_123_24,
                text = EpisodeFilterType.episodeFilterByCode,
                isSelected = extraFiltersData.episodeQueryType == EpisodeFilterType.episodeFilterByCode
            ) {
                changeExtraFiltersData(extraFiltersData.changeQueryType(EpisodeFilterType.episodeFilterByCode))
            }
        }
    }
}