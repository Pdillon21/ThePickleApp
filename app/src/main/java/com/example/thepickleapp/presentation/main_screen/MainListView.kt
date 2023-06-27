package com.example.thepickleapp.presentation.main_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thepickleapp.data.dao.PickleResultDaoBase
import com.example.thepickleapp.data.dao.PickleResultDaoBase.LocationDao
import com.example.thepickleapp.data.dao.PickleResultDaoBase.EpisodeDao
import com.example.thepickleapp.data.dao.PickleResultDaoBase.CharacterDao
import com.example.thepickleapp.presentation.common_views.CharacterColumnCell
import com.example.thepickleapp.presentation.common_views.EpisodeColumnCell
import com.example.thepickleapp.presentation.common_views.LocationColumnCell

@Composable
fun MainListView(
    listState: List<PickleResultDaoBase>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(items = listState, itemContent = { singleItem ->
            when (singleItem) {
                is CharacterDao -> {
                    CharacterColumnCell(singleItem)
                }

                is EpisodeDao -> {
                    EpisodeColumnCell(singleItem)
                }

                is LocationDao -> {
                    LocationColumnCell(singleItem)
                }
            }
        })
    }
}