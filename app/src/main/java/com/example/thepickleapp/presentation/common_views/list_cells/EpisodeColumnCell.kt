package com.example.thepickleapp.presentation.common_views.list_cells

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.thepickleapp.data.dao.PickleResultDaoBase.EpisodeDao
import com.example.thepickleapp.presentation.common_views.BasePickleCard

@Composable
fun EpisodeColumnCell(item: EpisodeDao) {
    BasePickleCard {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            //ToDo
            Text(text = item.name ?: "NoName")
        }
    }
}