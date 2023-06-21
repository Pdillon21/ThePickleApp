package com.example.thepickleapp.presentation.common_views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.thepickleapp.data.dao.character.CharacterDao

@Composable
fun CharacterColumnCell(singleItem: CharacterDao) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2.5f)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
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
            .fillMaxHeight()
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
