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
import com.example.thepickleapp.presentation.common_views.list_cells.CharacterColumnCell
import com.example.thepickleapp.presentation.common_views.list_cells.EpisodeColumnCell
import com.example.thepickleapp.presentation.common_views.list_cells.LocationColumnCell
import kotlin.random.Random

@Composable
fun MainListView(
    listState: List<PickleResultDaoBase>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(items = listState, key = { getKeyForDao(it) }, itemContent = { singleItem ->
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

fun getKeyForDao(dao: PickleResultDaoBase): Int {
    return when (dao) {
        is CharacterDao -> {
            dao.id ?: Random.nextInt(0, 1000000000)
        }

        is LocationDao -> {
            dao.id ?: Random.nextInt(0, 1000000000)
        }
        is EpisodeDao -> {
            dao.id ?: Random.nextInt(0, 1000000000)
        }
    }
}
