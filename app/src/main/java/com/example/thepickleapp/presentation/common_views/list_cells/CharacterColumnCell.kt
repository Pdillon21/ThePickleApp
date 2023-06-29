package com.example.thepickleapp.presentation.common_views.list_cells

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thepickleapp.data.dao.PickleResultDaoBase.CharacterDao
import com.example.thepickleapp.presentation.common_views.BasePickleCard
import com.example.thepickleapp.presentation.common_views.PickleImageView

@Composable
fun CharacterColumnCell(singleItem: CharacterDao) {
    BasePickleCard {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            CharacterImageView(singleItem.image, singleItem.id)
            CharacterData(singleItem)
        }
    }
}

@Composable
fun CharacterImageView(image: String?, id: Int?) {
    Card(
        modifier = Modifier
            .height(80.dp)
            .aspectRatio(1f)
            .padding(8.dp)
    ) {
        PickleImageView(url = image, id = id.toString())
    }
}

@Composable
fun CharacterData(singleItem: CharacterDao) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        //Todo Pasar strings a values
        Text(text = singleItem.name ?: "Unknown")
        Text(text = singleItem.created ?: "Unknown")
        Text(text = singleItem.gender ?: "Unknown")
    }
}
