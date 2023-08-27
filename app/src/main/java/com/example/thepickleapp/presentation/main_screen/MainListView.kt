package com.example.thepickleapp.presentation.main_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thepickleapp.data.dao.CharacterDao
import com.example.thepickleapp.data.dao.EpisodeDao
import com.example.thepickleapp.data.dao.LocationDao
import com.example.thepickleapp.data.dao.PickleResultDaoBase
import com.example.thepickleapp.presentation.common_views.general.EndOfListView
import com.example.thepickleapp.presentation.common_views.list_cells.CharacterColumnCell
import com.example.thepickleapp.presentation.common_views.list_cells.EpisodeColumnCell
import com.example.thepickleapp.presentation.common_views.list_cells.LocationColumnCell

@Composable
fun MainListView(
    listState: List<PickleResultDaoBase>,
    isPaging: Boolean,
    noMoreResults: Boolean,
    endWasReached: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(listState.size) { i ->
            val item = listState[i]
            if (i >= listState.size - 1 && !isPaging && !noMoreResults) {
                endWasReached()
            }
            when (item) {
                is CharacterDao -> {
                    CharacterColumnCell(item)
                }

                is EpisodeDao -> {
                    EpisodeColumnCell(item)
                }

                is LocationDao -> {
                    LocationColumnCell(item)
                }
            }
        }
        item {
            if (isPaging) {
                PagingCell()
            }
            if (noMoreResults) {
                EndOfListView()
            }
        }
    }
}

@Composable
fun PagingCell() {
    Text(text = "Paging")
}
