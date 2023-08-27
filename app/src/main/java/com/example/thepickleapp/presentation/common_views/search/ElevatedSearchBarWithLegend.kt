package com.example.thepickleapp.presentation.common_views.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.thepickleapp.R

@Composable
fun ElevatedSearchBarWithLegend(
    showDynamicLegend: Boolean = true,
    hint: String?,
    legend: String?,
    query: String?,
    onQueryChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        legend?.let {
            Text(
                text = legend + if (showDynamicLegend) {
                    query ?: stringResource(id = R.string.EMPTY_STRING)
                } else {
                    stringResource(id = R.string.EMPTY_STRING)
                }
            )
        }
        PickleElevatedSearchBar(
            currentQuery = query,
            buttonVisible = false,
            hint = hint,
            onQuerryChange = { onQueryChange(it) }
        )
    }
}
