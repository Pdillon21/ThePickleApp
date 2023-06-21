package com.example.thepickleapp.presentation.main_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thepickleapp.data.dao.character.CharacterResultContainer
import com.example.thepickleapp.presentation.common_views.CharacterColumnCell

@Composable
fun MainSuccessScreen(characterResultContainer: CharacterResultContainer) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(items = characterResultContainer.results, itemContent = { singleItem ->
            CharacterColumnCell(singleItem)
        })
    }
}