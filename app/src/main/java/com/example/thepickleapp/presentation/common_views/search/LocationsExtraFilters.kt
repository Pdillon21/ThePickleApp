package com.example.thepickleapp.presentation.common_views.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.thepickleapp.R
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
            ElevatedSearchBarWithLegend(
                legend = stringResource(R.string.location_type_legend_capitalyzed),
                query = extraFiltersData.type,
                hint = stringResource(R.string.type_of_location_hint)
            ) { newTypeQuery ->
                changeExtraFiltersData(extraFiltersData.changeType(newTypeQuery))
            }
            Spacer(modifier = Modifier.height(8.dp))
            ElevatedSearchBarWithLegend(
                legend = stringResource(R.string.dimension_legend_capitalyzed),
                query = extraFiltersData.dimension,
                hint = stringResource(R.string.name_of_dimension_hint)
            ) { newDimensionQuery ->
                changeExtraFiltersData(extraFiltersData.changeDimension(newDimensionQuery))
            }
        }
    }
}