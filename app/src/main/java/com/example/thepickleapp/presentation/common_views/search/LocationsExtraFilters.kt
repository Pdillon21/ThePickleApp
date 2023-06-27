package com.example.thepickleapp.presentation.common_views.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thepickleapp.presentation.main_screen.search.state.ExtraFiltersData
import com.example.thepickleapp.presentation.main_screen.search.state.changeDimension
import com.example.thepickleapp.presentation.main_screen.search.state.changeType

@Composable
fun LocationExtraFiltersContainer(
    extraFiltersData: ExtraFiltersData,
    changeExtraFiltersData: (ExtraFiltersData) -> Unit
) {
    if (extraFiltersData is ExtraFiltersData.LocationsExtraFilters) {
        Column(modifier = Modifier.fillMaxWidth()) {
            HighlightPickleSearchBar(
                legend = "Location Type: ",
                query = extraFiltersData.type
            ) { newTypeQuery ->
                changeExtraFiltersData(extraFiltersData.changeType(newTypeQuery))
            }
            Spacer(modifier = Modifier.height(8.dp))
            HighlightPickleSearchBar(
                legend = "Dimension: ",
                query = extraFiltersData.dimension
            ) { newDimensionQuery ->
                changeExtraFiltersData(extraFiltersData.changeDimension(newDimensionQuery))
            }
        }
    }
}